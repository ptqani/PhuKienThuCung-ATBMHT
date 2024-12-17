package controllers.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import models.Order;
import models.OrderDetail;
import service.OrderService;

@WebServlet("/order-details")
public class ViewOdersServlet extends HttpServlet {
	private OrderDAO orderDAO = new OrderDAO();
	private OrderService orderService = new OrderService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int orderId = Integer.parseInt(req.getParameter("orderId"));
			List<OrderDetail> orderDetails;
			orderDetails = orderDAO.getOrderDetailsByOrderId(orderId);
			Order order = orderDAO.getOrderById(orderId);
			String hashOrder = orderService.generateOrderHash(orderId);
			req.setAttribute("order", order);
			req.setAttribute("orderDetails", orderDetails);
			req.setAttribute("hashOrder", hashOrder);
			req.getRequestDispatcher("/WEB-INF/view-orders.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
