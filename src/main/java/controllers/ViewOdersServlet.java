package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Database;
import models.Order;
import models.OrderDetail;

@WebServlet("/orderdetails")
public class ViewOdersServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderIdParam = req.getParameter("ordeid");

		Database dao = new Database();
		int orderId = Integer.parseInt(orderIdParam);
		List<OrderDetail> orderDetails;
		try {
			orderDetails = dao.getOrderDetailsByOrderId(orderId);
			Order order = dao.getOrderById(orderId);
			req.setAttribute("order", order);
			req.setAttribute("orderDetails", orderDetails);
			req.getRequestDispatcher("/WEB-INF/view-oders.jsp").forward(req, resp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
