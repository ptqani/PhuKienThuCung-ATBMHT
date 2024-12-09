package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Database;
import models.User;

@WebServlet("/generate-key")
public class genKey extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tạo KeyPairGenerator cho DSA
			int keySize = 512; // Kích thước khóa
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			keyGen.initialize(keySize, random);

			// Tạo cặp khóa DSA
			KeyPair keyPair = keyGen.generateKeyPair();

			// Lấy khóa công khai và riêng tư dưới dạng Base64
			String publicKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
			// Lấy user từ session
			User user = (User) req.getSession().getAttribute("user");
			int userId = user.getId();
			// Cập nhật khóa công khai vào cơ sở dữ liệu
			Database dao = new Database();
			boolean isUpdated = dao.upsertKey(userId, publicKeyBase64);

			String privateKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

			// Chỉ gửi privateKey dưới dạng file
			String fileContent = privateKeyBase64;

			// Đặt headers để trình duyệt xử lý tải file
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment;filename=privateKey.txt");
			resp.setContentLength(fileContent.length());

			// Ghi nội dung file ra output stream
			try (OutputStream os = resp.getOutputStream()) {
				os.write(fileContent.getBytes());
				os.flush(); // Đảm bảo dữ liệu được gửi hết
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
