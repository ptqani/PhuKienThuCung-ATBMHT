package controllers;


import dao.Database;
import models.Order;
import utils.EmailUtil;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/process-payment")
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Database dao = new Database();
        // Lấy thông tin từ request
        String orderId = request.getParameter("orderId");
        Order order = null; // Lấy thông tin đơn hàng từ CSDL
        try {
            order = dao.getOrderById(Integer.parseInt(orderId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (order != null) {
            // Sử dụng hàm hashOrderData để tính mã hash từ thông tin đơn hàng
            String calculatedHash = HashUtil.hashOrderData(order.getUser_id(), order.getName(), order.getPhone(),
                    order.getEmail_address(), order.getShipping_address(), order.getTotal_price());

            // So sánh mã hash tính toán với mã hash lưu trong CSDL
            if (!calculatedHash.equals(order.getOrder_hash())) {
                // Nếu mã hash khác nhau, hủy đơn hàng và gửi email
                order.setStatus("Cancelled");
                try {
                    dao.updateOrderStatus(order.getOrder_id(),order.getStatus()); // Cập nhật trạng thái đơn hàng
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                sendCancellationEmail(order); // Gửi email thông báo hủy đơn hàng
                request.getRequestDispatcher("/WEB-INF/order-failed.jsp").forward(request, response);
            } else {
                // Nếu mã hash giống nhau, tiến hành thanh toán
                order.setStatus("Complete");
                try {
                    dao.updateOrderStatus(order.getOrder_id(),order.getStatus()); // Cập nhật trạng thái thanh toán
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                sendSuccessEmail(order); // Gửi email thông báo thanh toán thành công
                request.getRequestDispatcher("/WEB-INF/payment-success.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/order-not-found.jsp").forward(request,response); // Nếu không tìm thấy đơn hàng
        }
    }

    private void sendCancellationEmail(Order order) {
        // Gửi email thông báo hủy đơn hàng
        String to = order.getEmail_address();
        String subject = "Đơn hàng bị hủy";
        String message = "Đơn hàng #" + order.getEmail_address() + " đã bị hủy vì thông tin thay đổi.";
        EmailUtil.sendEmail(to, subject, message); // Gọi phương thức sendEmail từ EmailUtil
    }

    private void sendSuccessEmail(Order order) {
        // Gửi email thông báo thanh toán thành công
        String to = order.getEmail_address();
        String subject = "Thanh toán thành công";
        String message = "Cảm ơn bạn đã thanh toán đơn hàng #" + order.getOrder_id() + ". Chúng tôi đã nhận được thanh toán của bạn.";
        EmailUtil.sendEmail(to, subject, message); // Gọi phương thức sendEmail từ EmailUtil
    }

}
