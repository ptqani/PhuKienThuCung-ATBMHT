package controllers;

import java.io.IOException;
import java.io.PrintWriter;

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
		// Lấy giá trị 'orderId' và 'authToken' từ URL
		String orderIdParam = request.getParameter("orderId");
		String authToken = request.getParameter("authToken");

		if (authToken == null || authToken.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
			response.getWriter().println("{\"error\": \"Invalid or missing authToken\"}");
			return;
		}

		// Kiểm tra orderId có hợp lệ không
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
					response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
					out.println("Order not found");
				}

				out.flush();
			} catch (NumberFormatException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
				response.getWriter().println("{\"error\": \"Invalid orderId format\"}");
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
				response.getWriter().println("{\"error\": \"Internal server error\"}");
			}
		} else {
			// Nếu không có orderId trong URL
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
			response.getWriter().println("{\"error\": \"Missing orderId\"}");
		}
	}

}