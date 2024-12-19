package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Database;
import models.User;

@WebServlet("/generate-key")
public class GenKey extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Khởi tạo KeyPairGenerator cho RSA
            int keySize = 2048; // Kích thước khóa
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            // Sử dụng SecureRandom mạnh mẽ hơn
            SecureRandom random = SecureRandom.getInstanceStrong();
            keyGen.initialize(keySize, random);

            // Tạo cặp khóa RSA
            KeyPair keyPair = keyGen.generateKeyPair();

            // Lấy khóa công khai và riêng tư dưới dạng Base64
            String publicKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
            
            // Lấy user từ session
            User user = (User) req.getSession().getAttribute("user");
            int userId = user.getId();

            // Cập nhật khóa công khai vào cơ sở dữ liệu
            Database dao = new Database();
            boolean isUpdated = dao.upsertKey(userId, publicKeyBase64, "active");

            // Lấy khóa riêng tư dưới dạng Base64
            String privateKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

            // Đặt headers để trình duyệt xử lý tải file
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment;filename=privateKey.txt");
            resp.setContentLength(privateKeyBase64.length());

            // Ghi nội dung file ra output stream
            try (OutputStream os = resp.getOutputStream()) {
                os.write(privateKeyBase64.getBytes());
                os.flush(); // Đảm bảo dữ liệu được gửi hết
            }
          
			String publickey = dao.getPublicKeyByUserId(userId);
			req.setAttribute("key", publickey);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No such algorithm: " + e.getMessage());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        }
    }
}

