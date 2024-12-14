package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.Database;
import models.User;
@WebServlet("/upload-publickey")
@MultipartConfig
public class UploadPublickeyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy thông tin người dùng từ session
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();

        // Lấy file public key từ request
        Part filePart = req.getPart("publickeyFile");

        // Đọc dữ liệu từ file
        InputStream fileContent = filePart.getInputStream();
        byte[] publicKeyBytes = new byte[fileContent.available()];
        fileContent.read(publicKeyBytes);

        // Lưu public key vào cơ sở dữ liệu
     
            Database dao = new Database();
            try {
				boolean success = dao.updatePublicKeyByUserId(userId, publicKeyBytes,"Updated");
				resp.sendRedirect("url?page=keygen");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         
 

     
    }
}
