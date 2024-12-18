package controllers;

import dao.Database;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/report-key")
public class ReportKeyServlet extends HttpServlet {
    Database jdbcUtil = new Database();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        try (Connection conn = jdbcUtil.getConnection()) {

            // 1. Vô hiệu hóa key hiện tại
            String updateQuery = "UPDATE `key` SET end_time = NOW(), status = 'inactive' WHERE iduser = ? AND status = 'active'";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, userId);
                updateStmt.executeUpdate();
            }

            // 2. Kiểm tra xem user có key "active" chưa
            String checkActiveKeyQuery = "SELECT COUNT(*) FROM `key` WHERE iduser = ? AND status = 'active'";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkActiveKeyQuery)) {
                checkStmt.setInt(1, userId);
                var rs = checkStmt.executeQuery();
                rs.next();
                int activeKeyCount = rs.getInt(1);

                // Nếu đã có key active, không cho tạo thêm key
                if (activeKeyCount > 0) {
                    response.getWriter().println("Error: User already has an active key. Deactivate it before generating a new one.");
                    return;
                }
            }
            // 3. Tạo key mới
            String createKeyQuery = "INSERT INTO `key` (iduser, publickey, created_day, end_time, status) " +
                    "VALUES (?, UUID(), NOW(), NULL, 'active')";
            try (PreparedStatement createStmt = conn.prepareStatement(createKeyQuery)) {
                createStmt.setInt(1, userId);
                createStmt.executeUpdate();
            }

            response.getWriter().println("Key reported as lost, deactivated successfully. New key generated and activated.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
