package controllers;

import dao.Database;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

@WebServlet("/report-key")
public class ReportKeyServlet extends HttpServlet {
    Database jdbcUtil = new Database();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy user từ session
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();

            // 1. Vô hiệu hóa key hiện tại
            // 1. Vô hiệu hóa key hiện tại
            try (Connection conn = jdbcUtil.getConnection()) {
                String updateQuery = "UPDATE `key` SET end_time = NOW(), status = 'inactive' WHERE iduser = ? AND status = 'active'";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, userId);
                    int rowsUpdated = updateStmt.executeUpdate();

                    if (rowsUpdated == 0) {
                        throw new SQLException("Không tìm thấy key active để vô hiệu hóa.");
                    }
                }
            }

            // 2. Tạo key mới
            int keySize = 2048;
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstanceStrong();
            keyGen.initialize(keySize, random);

            KeyPair keyPair = keyGen.generateKeyPair();
            String publicKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            String privateKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

            // 3. Cập nhật key mới vào cơ sở dữ liệu
            boolean isUpdated = jdbcUtil.upsertKey(userId, publicKeyBase64, "active");

            if (!isUpdated) {
                throw new SQLException("Không thể cập nhật key mới vào cơ sở dữ liệu.");
            }

            // 4. Gửi khóa riêng tư về cho người dùng
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=privateKey.txt");
            response.setContentLength(privateKeyBase64.length());

            response.getOutputStream().write(privateKeyBase64.getBytes());
            response.getOutputStream().flush();

            // 5. Chuyển hướng về trang genkey
            request.getRequestDispatcher("/WEB-INF/genKey.jsp").forward(request,response);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Không tìm thấy thuật toán RSA: " + e.getMessage(), e);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Lỗi khi xử lý cơ sở dữ liệu: " + e.getMessage(), e);
        }
    }
}
