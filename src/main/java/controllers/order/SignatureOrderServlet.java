package controllers.order;

import dao.KeyDAO;
import dao.OrderDAO;
import models.Order;
import models.OrderDetail;
import models.User;
import service.OrderService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signature-order")
public class SignatureOrderServlet extends HttpServlet {
    private OrderDAO orderDAO = new OrderDAO();
    private OrderService orderService = new OrderService();
    private User user;
    private String publicKey;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            checkUserKey(request, response);
            List<OrderDetail> orderDetails = orderDAO.getOrderDetailsByOrderId(orderId);
            Order order = orderDAO.getOrderById(orderId);
            String hashOrder = orderService.generateOrderHash(orderId);
            request.setAttribute("order", order);
            request.setAttribute("orderDetails", orderDetails);
            request.setAttribute("hashOrder", hashOrder);
            request.getRequestDispatcher("/WEB-INF/view-orders.jsp").forward(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            checkUserKey(request, response);
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            String signature = request.getParameter("signature").trim();
            boolean isSigned = orderService.verifySignature(orderId, publicKey, signature);
            if (isSigned) {
                orderDAO.updateOrderSignature(orderId, signature);
                request.setAttribute("message", "Order successfully signed. Continue shopping");
                request.getRequestDispatcher("/WEB-INF/notification.jsp").forward(request, response);
            } else {
                // Gửi phản hồi thất bại nếu không tìm thấy orderId
                request.setAttribute("errorMessage", "Order signed unsuccessfully. Click link below to review order!");
                request.setAttribute("linkGoto", request.getContextPath() + "/signature-order?orderId=" + orderId);
                request.getRequestDispatcher("/WEB-INF/notification.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void checkUserKey(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            user = (User) session.getAttribute("user");

            if (user == null) {
                request.setAttribute("errorMessage", "Please Login");
                request.setAttribute("linkGoto", request.getContextPath() + "/login");
                request.getRequestDispatcher("/WEB-INF/notification.jsp").forward(request, response);
            }
            publicKey = KeyDAO.getPublicKeyByUserId(user.getId());
            if (publicKey == null) {
                request.setAttribute("errorMessage", "User does not have public key");
                request.setAttribute("linkGoto", request.getContextPath() + "/url?page=key");
                request.getRequestDispatcher("/WEB-INF/notification.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
