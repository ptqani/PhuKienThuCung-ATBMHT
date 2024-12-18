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

    public void sendCancellationEmail(Order order) {
        // Gửi email thông báo hủy đơn hàng
        String to = order.getEmail_address();
        System.out.println(to);
        String subject = "Đơn hàng bị hủy";
        String message = "Đơn hàng #" + order.getOrder_id()+ " đã bị hủy vì có sự thay đổi bất hợp pháp.Chúng tôi" +
                " xin lỗi vì sự bất tiện này";
        EmailUtil.sendEmail(to, subject, message); // Gọi phương thức sendEmail từ EmailUtil
    }

    public void sendSuccessEmail(Order order) {
        // Gửi email thông báo thanh toán thành công cùng với chi tiết hóa đơn
        String to = order.getEmail_address();
        String subject = "Thanh toán thành công - Hóa đơn #" + order.getOrder_id();

        // Tạo nội dung email chứa thông tin hóa đơn
        String message = "Cảm ơn bạn đã thanh toán đơn hàng #" + order.getOrder_id() + ". Chúng tôi đã nhận được thanh toán của bạn.\n\n";
        message += "Thông tin đơn hàng:\n";
        message += "Tên người nhận: " + order.getName() + "\n";
        message += "Số điện thoại: " + order.getPhone() + "\n";
        message += "Địa chỉ giao hàng: " + order.getShipping_address() + "\n";
        message += "Tổng giá trị đơn hàng: " + order.getTotal_price() + " VNĐ\n";
        // Thêm thông báo cám ơn và thông tin thanh toán thành công
        message += "Chúng tôi rất cảm ơn bạn đã tin tưởng và sử dụng dịch vụ của chúng tôi.\n";
        message += "Mọi thắc mắc vui lòng liên hệ với chúng tôi qua email này.\n";

        // Gửi email
        EmailUtil.sendEmail(to, subject, message); // Gọi phương thức sendEmail từ EmailUtil
    }


}
