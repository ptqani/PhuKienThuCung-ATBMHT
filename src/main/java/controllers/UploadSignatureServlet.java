package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Database;

@WebServlet("/upload-signature")
public class UploadSignatureServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String signature = request.getParameter("signature");
		Database dao = new Database();
		try {
			boolean isUpdated = dao.updateOrderSignature(orderId, signature);
			if (isUpdated) {
				// Gửi phản hồi thành công
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write("Chữ ký đã được cập nhật thành công.");
			} else {
				// Gửi phản hồi thất bại nếu không tìm thấy orderId
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Không tìm thấy đơn hàng với ID: " + orderId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
