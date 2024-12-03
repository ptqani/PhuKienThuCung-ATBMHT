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

@WebServlet({"/", "/home"})
public class Home extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Database dao = new Database();

			// Lấy 6 sản phẩm mới nhất
			List<Product> listProductdesc = dao.getLatestProducts();

			// Truyền danh sách sản phẩm mới nhất vào request để hiển thị trên home.jsp
			request.setAttribute("listProductdesc", listProductdesc);
			
			List<Product> listRandomProductc = dao.getRandomProducts();
			request.setAttribute("listRandomProductc", listRandomProductc);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// Hiển thị thông báo lỗi trong trường hợp có ngoại lệ xảy ra
			request.setAttribute("errorMessage", "Đã xảy ra lỗi khi kết nối đến cơ sở dữ liệu. Vui lòng thử lại sau.");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
