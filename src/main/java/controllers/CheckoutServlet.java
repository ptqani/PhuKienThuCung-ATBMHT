package controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

			// Tạo mã token ngẫu nhiên
			String token = java.util.UUID.randomUUID().toString();

			// Gửi email với mã token
			sendEmailOrder(email, token);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// Xóa giỏ hàng khỏi session sau khi hoàn thành checkout
		HttpSession session = request.getSession();
		session.removeAttribute("cart"); // Xóa giỏ hàng

		// Chuyển hướng người dùng tới trang xác nhận đơn hàng
		request.getRequestDispatcher("/WEB-INF/view-oders.jsp").forward(request, response);
	}

	private boolean isOrderHashValid(int orderId, String expectedHash) {
		Database dao = new Database();
		try {
			Order order = dao.getOrderById(orderId);
			if (order == null) {
				return false;  // Đơn hàng không tồn tại
			}

			String actualHash = order.getOrder_hash();

			// So sánh hash hiện tại với hash dự đoán
			return expectedHash.equals(actualHash);
		} catch (Exception e) {
			e.printStackTrace();
			return false;  // Lỗi trong quá trình kiểm tra
		}
	}
	private boolean isValidOrderHash(int orderId, String originalHash) throws SQLException, ClassNotFoundException {
		// Lấy đơn hàng từ cơ sở dữ liệu
		Database dao = new Database();
		Order order = dao.getOrderById(orderId);

		if (order != null) {
			// So sánh mã hash trong cơ sở dữ liệu với mã hash gốc
			return originalHash.equals(order.getOrder_hash());
		}

		return false;
	}

	// Phương thức gửi email với mã token
	private void sendEmailOrder(String recipientEmail, String token) {
		String host = "smtp.gmail.com";  // Địa chỉ SMTP server
		String from = "vanluan0903@gmail.com";  // Địa chỉ email gửi
		String password = "hgov myiy ltpd ltqh";

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject("Your Order Token");
			message.setText("Cảm ơn bạn đã đặt hàng, mã token của bạn là: " + token);

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
