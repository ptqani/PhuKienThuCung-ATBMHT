package controllers.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import models.Cart;
import models.Order;
import models.OrderDetail;
import models.User;
import service.OrderService;

@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy các tham số từ form
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String country = request.getParameter("country");
            String state = request.getParameter("state");
            String zip = request.getParameter("zip");
            // Lấy tham số totalCurrency từ form
            String totalCurrency = request.getParameter("totalCurrency");

            // Loại bỏ ký tự không phải số (dấu "$" và dấu phẩy ",")
            totalCurrency = totalCurrency.replaceAll("[^\\d]", ""); // Xóa tất cả ký tự không phải là chữ số

            // Chuyển đổi thành số nguyên
            int totalCurrencyInt = Integer.parseInt(totalCurrency);

            String alladdress = address + ", " + state + ", " + country + ", " + zip;
            // Lấy user từ session
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();
            // Tạo nội dung đơn hàng
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            int orderId = orderDAO.createOrder(userId, name, phone, email, totalCurrencyInt, address, "pending");
            orderDAO.saveOrderDetails(orderId, cart);
            // Xóa giỏ hàng khỏi session sau khi hoàn thành checkout
            HttpSession session = request.getSession();
            session.removeAttribute("cart"); // Xóa giỏ hàng
            response.sendRedirect(request.getContextPath() + "/signature-order?orderId=" + orderId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
