package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                    req.getRequestDispatcher("/WEB-INF/my-orders.jsp").forward(req, resp);
                    break;
                case "chagepass":
                    req.getRequestDispatcher("/WEB-INF/chage-password.jsp").forward(req, resp);
                    break;
                case "address":
                    req.getRequestDispatcher("/WEB-INF/addresses.jsp").forward(req, resp);
                    break;
                default:
                    req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
                    break;
            }
        }
    }
}
