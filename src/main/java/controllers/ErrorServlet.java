package controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the 404.jsp in the WEB-INF directory
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/404.jsp");
        dispatcher.forward(request, response);
    }
}
