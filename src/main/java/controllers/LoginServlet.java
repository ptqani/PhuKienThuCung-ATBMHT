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
import models.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	// Phương thức GET để hiển thị trang đăng nhập
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward đến trang login.jsp hoặc trang HTML tương ứng
		request.getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(request, response);
	}

	// Phương thức POST để xử lý đăng nhập
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy thông tin từ form
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Kiểm tra thông tin đăng nhập
		Database dao = new Database();
		User user = null;

		try {
			user = dao.loginUser(email, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			response.getWriter().write("Đăng nhập thất bại. Vui lòng thử lại.");
			return;
		}

		// Kiểm tra nếu có người dùng và mật khẩu chính xác
		if (user != null) {
			// Đăng nhập thành công, chuyển hướng đến trang chủ hoặc trang người dùng
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/home").forward(request, response);
		} else {
			// Đăng nhập thất bại
			request.setAttribute("errorMessage", "Sai email hoặc mật khẩu.");
			request.getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(request, response); // Hiển thị lỗi trên trang login.jsp
		}
	}
	

}
