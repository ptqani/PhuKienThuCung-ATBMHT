package controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Database;
import models.Cart;
import models.LineItem;
import models.Order;
import models.OrderDetail;
import models.User;

@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		StringBuilder orderContent = new StringBuilder();
		orderContent.append("Name: ").append(name).append("\n").append("Phone: ").append(phone).append("\n")
				.append("Email: ").append(email).append("\n").append("Shipping Address: ").append(alladdress)
				.append("\n").append("Country: ").append(country).append("\n").append("State: ").append(state)
				.append("\n").append("Zip Code: ").append(zip).append("\n").append("Total Currency: ")
				.append(totalCurrencyInt).append("\n\n");

		// Thêm chi tiết sản phẩm vào nội dung đơn hàng
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		for (LineItem detail : cart.getItems()) {
			orderContent.append("Product: ").append(detail.getProduct().getName()).append(", Quantity: ")
					.append(detail.getQuantity()).append(", Price: ").append(detail.getTotal()).append("\n");
		}

		// Băm hashOders sử dụng SHA-256
		String hashOders = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(orderContent.toString().getBytes("UTF-8"));
			// Chuyển mảng byte thành chuỗi hex
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				hexString.append(String.format("%02x", b));
			}
			hashOders = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		Database dao = new Database();

		int orderId;
		try {
			orderId = dao.createOrder(userId, name, phone, email, totalCurrencyInt, address, "pending", hashOders);
			boolean orderDetailsSaved = dao.saveOrderDetails(orderId, cart);
			Order order = dao.getOrderById(orderId);
			request.setAttribute("order", order);
			List<OrderDetail> orderDetails = dao.getOrderDetailsByOrderId(orderId);
			request.setAttribute("orderDetails", orderDetails);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Xóa giỏ hàng khỏi session sau khi hoàn thành checkout
		HttpSession session = request.getSession();
		session.removeAttribute("cart"); // Xóa giỏ hàng

		// Gọi phương thức saveOrderDetails để lưu chi tiết đơn hàng

		request.getRequestDispatcher("/WEB-INF/view-oders.jsp").forward(request, response);

	}
}
