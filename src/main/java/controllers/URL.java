package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Database;
import models.Order;
import models.User;

@WebServlet("/url")
public class URL extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");

		if (page == null) {
			req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
		} else {
			switch (page) {
			case "login":
				req.getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(req, resp);
				break;
			case "cart":
				req.getRequestDispatcher("/WEB-INF/shopping-cart.jsp").forward(req, resp);
				break;
			case "checkout":
				req.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(req, resp);
				break;
			case "dashboard":
				req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
				break;
			case "myoders":
				try {
					handleOders(req, resp);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "chagepass":
				req.getRequestDispatcher("/WEB-INF/chage-password.jsp").forward(req, resp);
				break;
			case "address":
				req.getRequestDispatcher("/WEB-INF/addresses.jsp").forward(req, resp);
				break;
			case "keygen":
				try {
					handleKeygen(req, resp);
				} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "logout":
				handleLogout(req, resp);
				break;
			default:
				req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
				break;
			}
		}
	}

	private void handleOders(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		// Lấy thông tin người dùng từ session
		User user = (User) req.getSession().getAttribute("user");
		int userId = user.getId();
		Database dao = new Database();
		List<Order> orders = dao.getOrdersByUserId(userId);
		// Lưu danh sách đơn hàng vào request để hiển thị trên trang
		req.setAttribute("orders", orders);
		req.getRequestDispatcher("/WEB-INF/my-orders.jsp").forward(req, resp);

	}

	private void handleKeygen(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		User user = (User) req.getSession().getAttribute("user");
		int userId = user.getId();
		Database dao = new Database();
		String publickey = dao.getPublicKeyByUserId(userId);
		req.setAttribute("key", publickey);
		req.getRequestDispatcher("/WEB-INF/keyGen.jsp").forward(req, resp);

	}

	/**
	 * Phương thức xử lý đăng xuất
	 */
	private void handleLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// Lấy session hiện tại, nếu có
		HttpSession session = req.getSession(false); // false: không tạo session mới nếu chưa có

		if (session != null) {
			// Hủy session
			session.invalidate();
		}
		req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
	}
}
