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
import utils.HashUtil;

@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String totalCurrency = request.getParameter("totalCurrency");
		totalCurrency = totalCurrency.replaceAll("[^\\d]", "");
		int totalCurrencyInt = Integer.parseInt(totalCurrency);

		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();

		// Băm thông tin cơ bản
		String hashOders = HashUtil.hashOrderData(userId, name, phone, email, address, totalCurrencyInt);

		Database dao = new Database();
		try {
			int orderId = dao.createOrder(userId, name, phone, email, totalCurrencyInt, address, "pending", hashOders);
			boolean orderDetailsSaved = dao.saveOrderDetails(orderId, (Cart) request.getSession().getAttribute("cart"));
			Order order = dao.getOrderById(orderId);
			request.setAttribute("order", order);
			List<OrderDetail> orderDetails = dao.getOrderDetailsByOrderId(orderId);
			request.setAttribute("orderDetails", orderDetails);

			String token = java.util.UUID.randomUUID().toString();
			sendEmailOrder(email, token);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		request.getRequestDispatcher("/WEB-INF/view-oders.jsp").forward(request, response);
	}

	private void sendEmailOrder(String recipientEmail, String token) {
		String host = "smtp.gmail.com";
		String from = "vanluan0903@gmail.com";
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
