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
import models.Category;
import models.Product;

@WebServlet("/cate")
public class CategoryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryIdParam = request.getParameter("categoryId");
		// Kiểm tra nếu có ID danh mục
		if (categoryIdParam != null) {
			try {
				int categoryId = Integer.parseInt(categoryIdParam); // Chuyển đổi từ String sang int
				Database dao = new Database();

				// Lấy danh sách sản phẩm theo danh mục
				List<Product> productList = dao.getProductsByCategory(categoryId);
				// Gửi danh sách sản phẩm vào request để hiển thị trên JSP
				request.setAttribute("productList", productList);

				// Gửi danh mục hiện tại vào request để hiển thị tiêu đề
				List<Category> listCategory = dao.getAllCategories();
				request.setAttribute("listCategory", listCategory);
				
				// Lay 3 san pham ngua nhien
				List<Product> listRandomProductc = dao.getRandomProducts();
				request.setAttribute("listRandomProductc", listRandomProductc);
				
				// Chuyển hướng đến trang JSP hiển thị sản phẩm (ví dụ: category.jsp)
				request.getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
			} catch (SQLException | ClassNotFoundException e) {
				e.fillInStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi lấy dữ liệu.");
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID danh mục không hợp lệ.");
		}
	}
}
