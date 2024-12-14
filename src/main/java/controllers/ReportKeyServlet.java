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

        try {
            String updateQuery = "UPDATE `key` SET end_time = NOW(), status = 'inactive' WHERE iduser = ? AND status = 'active' AND end_time IS NULL";
            try (Connection conn = jdbcUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            String createKeyQuery = "INSERT INTO `key` (iduser, publickey, created_day, end_time, status) " +
                    "VALUES (?, UUID(), NOW(), NULL, 'active')";
            try (Connection conn = jdbcUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(createKeyQuery)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            response.getWriter().println("Key reported as lost, deactivated successfully. New key generated and activated.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
