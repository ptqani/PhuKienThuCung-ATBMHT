package controllers.key;

import dao.JDBCUtil;
import dao.KeyDAO;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "reportkey", value = "/reportkey")
public class ReportLostKeyServlet extends HttpServlet {
    JDBCUtil jdbcUtil = new JDBCUtil();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            // update public key trong UserSection
            User user = (User) session.getAttribute("user");
            if (user == null) {
                request.setAttribute("errorMessage", "Vui lòng đăng nhập.");
                request.getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(request, response); // Hiển thị lỗi trên trang login.jsp
            }

            int userId = Integer.parseInt(request.getParameter("user_id"));
            KeyDAO.reportLostKey(userId);

            user.setPublicKey(null);
            session.setAttribute("user", user);

            request.setAttribute("message", "Key đã được báo mất và vô hiệu hóa thành công!");

            // Forward về key.jsp để hiển thị kết quả
            request.getRequestDispatcher("/WEB-INF/account/key.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
