package controllers;

import dao.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private UserDAO userDAO = new UserDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Chuyển hướng người dùng đến trang đăng ký (nếu cần thiết)
		request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Lấy dữ liệu từ form đăng ký
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		// Kiểm tra mật khẩu xác nhận có khớp không
		if (!password.equals(confirmPassword)) {
			request.setAttribute("error", "Mật khẩu xác nhận không khớp.");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}

		try {
			boolean success = userDAO.registerUser(username, password, email, phone);
			if (success) {
				// Đăng ký thành công, chuyển hướng đến trang đăng nhập
				request.getRequestDispatcher("/home").forward(request, response);
			} else {
				// Nếu không thành công, thông báo lỗi và quay lại trang đăng ký
				request.setAttribute("error", "Đăng ký thất bại, vui lòng thử lại.");
				request.getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Đã có lỗi xảy ra, vui lòng thử lại sau.");
			request.getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(request, response);
		}
	}
}
