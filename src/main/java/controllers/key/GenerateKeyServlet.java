package controllers.key;

import dao.KeyDAO;
import models.User;
import service.KeyService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.security.KeyPair;
import java.util.Base64;

@WebServlet(name = "genkey", value = "/genkey")
public class GenerateKeyServlet extends HttpServlet {
    private KeyService keyService = new KeyService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                request.setAttribute("errorMessage", "Vui lòng đăng nhập.");
                request.getRequestDispatcher("/WEB-INF/sign-in.jsp").forward(request, response); // Hiển thị lỗi trên trang login.jsp
            }
            // Tạo cặp key
            KeyPair keyPair = keyService.generateKeyPair();
            String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

            // Lưu public key vào CSDL
            int userId = Integer.parseInt(request.getParameter("user_id"));
            KeyDAO.updateEndTimeForOldKeys(userId);
            KeyDAO.savePublicKeyToDatabase(userId, publicKey);

            // Lưu private key vào file .txt cho người dùng tải về
            String privateKeyFileName = "private_key_" + userId + ".txt";  // Tên file riêng biệt cho mỗi người dùng
            String privateKeyFilePath = getServletContext().getRealPath("/") + privateKeyFileName;  // Đường dẫn file trên server

            try (FileWriter fw = new FileWriter(privateKeyFilePath)) {
                fw.write(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));  // Lưu private key dạng Base64
            }
            // update public key trong UserSection
            user.setPublicKey(publicKey);
            session.setAttribute("user", user);


            request.setAttribute("message", "Cặp key đã được tạo thành công!");
            request.setAttribute("privateKeyFileName", privateKeyFileName);
            // Forward về key.jsp để hiển thị kết quả
            request.getRequestDispatcher("/WEB-INF/account/key.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating keys");
        }
    }
}
