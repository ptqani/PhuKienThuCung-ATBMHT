package controllers;

import dao.Database;
import util.EmailUtil;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/process-payment")
public class PaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String userHash = request.getParameter("orderHash");
        String orderSignature = request.getParameter("orderSignature");

        Database dao = new Database();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection();
            String query = "SELECT * FROM orders WHERE order_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String dbHash = rs.getString("order_hash");
                String dbSignature = rs.getString("order_signature");
                String orderStatus = rs.getString("status");
                int isVerified = rs.getInt("is_verified");
                String email = rs.getString("email_address");
                String customerName = rs.getString("name");

                // Kiểm tra xem đơn hàng có bị thay đổi không
                if (!dbHash.equals(userHash) || !dbSignature.equals(orderSignature)) {
                    // Nếu bị thay đổi, hủy đơn hàng và gửi email thông báo
                    String updateQuery = "UPDATE orders SET status = 'canceled' WHERE order_id = ?";
                    stmt = conn.prepareStatement(updateQuery);
                    stmt.setInt(1, orderId);
                    stmt.executeUpdate();

                    String cancelSubject = "Order Canceled";
                    String cancelContent = "Dear " + customerName + ",\n\nYour order has been canceled due to possible data manipulation. If you have any questions, please contact us.";
                    EmailUtil.sendEmail(email, cancelSubject, cancelContent);

                    response.getWriter().write("Order canceled due to data manipulation.");
                    return; // Kết thúc xử lý, không gửi hóa đơn nữa
                }

                // Kiểm tra xem đơn hàng có đã được xác minh không
                if (isVerified == 1 && orderStatus.equals("pending")) {
                    // Nếu đơn hàng đã xác minh và chưa thanh toán, chuyển trạng thái sang 'completed'
                    String updateQuery = "UPDATE orders SET status = 'completed' WHERE order_id = ?";
                    stmt = conn.prepareStatement(updateQuery);
                    stmt.setInt(1, orderId);
                    stmt.executeUpdate();

                    // Gửi email xác nhận và hóa đơn
                    String subject = "Order Confirmation";
                    String content = "Dear " + customerName + ",\n\nYour order has been successfully processed.\nOrder ID: " + orderId + "\nTotal: " + rs.getInt("total_price") + " VND\n\nThank you for shopping with us!";
                    EmailUtil.sendEmail(email, subject, content);

                    response.getWriter().write("Payment successful. Invoice has been sent.");
                } else {
                    response.getWriter().write("Order cannot be processed. Please verify the order first.");
                }
            } else {
                response.getWriter().write("Order not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while processing the payment.");
        } finally {
        }
    }
}
