package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Database;
import models.Order;

@WebServlet("/get-order-hash")
public class GetOrderHashServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy giá trị 'orderId' từ URL
		String orderIdParam = request.getParameter("orderId");
		if (orderIdParam != null && !orderIdParam.isEmpty()) {
			try {
				// Chuyển đổi orderId từ String sang int
				int orderId = Integer.parseInt(orderIdParam);

				// Tạo đối tượng Database để truy vấn
				Database dao = new Database();
				Order order = dao.getOrderById(orderId); // Lấy thông tin đơn hàng từ DB

				// Đặt kiểu trả về là JSON
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();

				if (order != null) {
					// Nếu tìm thấy đơn hàng, trả về thông tin
					out.println( order.getOrder_hash());
				} else {
					// Nếu không tìm thấy đơn hàng
					out.println("{\"error\": \"Order not found\"}");
				}

				out.flush();
			} catch (NumberFormatException e) {
				response.getWriter().println("{\"error\": \"Invalid orderId format\"}");
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			// Nếu không có orderId trong URL
			response.getWriter().println("{\"error\": \"Missing orderId\"}");
		}
	}
}
