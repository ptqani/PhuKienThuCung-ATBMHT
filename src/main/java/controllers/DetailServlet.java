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
import models.Product;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy id sản phẩm từ tham số URL
			String itemId = request.getParameter("id");
			Database dao = new Database();
			Product	product = dao.getProductById(itemId);
			request.setAttribute("product", product);
			
			// Lay 3 san pham ngua nhien
			List<Product> listRandomProductc = dao.getRandomProducts();
			request.setAttribute("listRandomProductc", listRandomProductc); 
			// Lay 6 san pham ngua nhien
			List<Product> getRandomSixProducts = dao.getRandomSixProducts();
			request.setAttribute("getRandomSixProducts", getRandomSixProducts); 
			
			
			request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
