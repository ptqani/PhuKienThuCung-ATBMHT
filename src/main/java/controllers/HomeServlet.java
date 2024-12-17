
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.ProductDAO;
import models.Category;
import models.Product;

@WebServlet({"/", "/home"})
public class HomeServlet extends HttpServlet {
	private ProductDAO productDAO = new ProductDAO();
	private CategoryDAO categoryDAO = new CategoryDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			// Lấy 6 sản phẩm mới nht
			List<Product> listProductdesc = productDAO.getLatestProducts();

			// Truyền danh sách sản phẩm mới nhất vào request để hiển thị trên home.jsp
			request.setAttribute("listProductdesc", listProductdesc);
			
			// Lay 3 san pham ngua nhien
			List<Product> listRandomProductc = productDAO.getRandomProducts();
			request.setAttribute("listRandomProductc", listRandomProductc);
			// Lay 6 san pham cu nhat
			List<Product> listProductold= productDAO.getOldSixProducts();
			request.setAttribute("listProductold", listProductold);
			
			//Lay 8 san pham co gia thap nhat
			List<Product> listProductcheap = productDAO.getEightCheapestProducts();
			request.setAttribute("listProductcheap", listProductcheap);
			
			//Lay 3 san pham moi nhat
			List<Product> listProductThree = productDAO.getLatestThreeProducts();
			request.setAttribute("listProductThree", listProductThree);
			
			//Lay 3 san pham cu nhat
			List<Product> listProductoldThree = productDAO.getOldThreeProducts();
			request.setAttribute("listProductoldThree", listProductoldThree);
			
			List<Category> listCategory = categoryDAO.getAllCategories();
			request.setAttribute("listCategory", listCategory);
			
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
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
