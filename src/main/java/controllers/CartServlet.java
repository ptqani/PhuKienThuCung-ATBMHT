package controllers;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.ProductDAO;
import models.Cart;
import models.LineItem;
import models.Product;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private ProductDAO productDAO = new ProductDAO();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// gán action = cart nếu rỗng
		String action = request.getParameter("action");
		if (action == null) {
			action = "cart";
		}

		// thực hiện hành động và đặt URL thành trang thích hợp
		String url = "/WEB-INF/home.jsp";
		if (action.equals("shop")) {
			url = "/WEB-INF/home.jsp"; // trang chủ
		} else if (action.equals("cart")) {
			String productCode = request.getParameter("productCode");
			String quantityString = request.getParameter("quantity");
// lấy cart từ ss ss
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
			} else {
				LineItem lineItem = new LineItem();
				lineItem.setQuantity(1 + lineItem.getQuantity());
			}

			// if the user enters a negative or invalid quantity,

			int quantity;
			try {
				quantity = Integer.parseInt(quantityString);
			} catch (NumberFormatException nfe) {
				quantity = 1;
			}

			Product product;
			try {
				// lấy sản phẩm theo id
				product = productDAO.getProductID(productCode);
				LineItem lineItem = new LineItem();
				lineItem.setProduct(product);
				lineItem.setQuantity(quantity);
				if (quantity > 0) {
					cart.addItem(lineItem);
				} else if (quantity == 0) {
					cart.removeItem(lineItem);
				} else if (quantity == -100) {
					cart.removeItem(lineItem);
					cart.Clear(lineItem);
				}

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("cart", cart);
			url = "/WEB-INF/shopping-cart.jsp";
		} else if (action.equals("checkout")) {
			url = "/WEB-INF/checkout.jsp";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}