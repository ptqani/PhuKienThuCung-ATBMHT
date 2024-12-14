package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Database;
import models.Order;

@WebServlet("/downloadOders")
public class DownloadOders extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderId = req.getParameter("orderId");
		Database dao = new Database();
		try {
			Order order = dao.getOrderById(Integer.parseInt(orderId));
			String orderHash = order.getOrder_hash(); // Lấy hash của đơn hàng
			  // Cấu hình phản hồi để trả về dữ liệu nhị phân (file)
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; filename=order_" + orderId + "_hash.txt");

            // Gửi nội dung hash cho người dùng tải xuống
            resp.getOutputStream().write(orderHash.getBytes());

		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
