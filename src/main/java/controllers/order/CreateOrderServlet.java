package controllers.order;

import dao.JDBCUtil;
import service.HashService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;



// Tạo đơn hàng và lưu hash
@WebServlet("/create-order")
public class CreateOrderServlet extends HttpServlet {
    JDBCUtil jdbcUtil = new JDBCUtil();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String orderData = request.getParameter("orderData");

        try {
            String orderHash = HashService.generateHash(orderData);

            // Lưu hash vào database
            String query = "INSERT INTO orders (user_id, order_hash) VALUES (?, ?)";
            try (Connection conn = jdbcUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                stmt.setString(2, orderHash);
                stmt.executeUpdate();
            }

            response.getWriter().println("Order created and hash saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}

