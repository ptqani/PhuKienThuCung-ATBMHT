package controllers;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Database;
import models.Order;
import models.User;
@WebServlet("/verification-form")
public class OrderVerification extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database dao = new Database();
        // Lấy user từ session
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        
        try {
            PublicKey publicKey = dao.PublicKeyByUserId(userId);
            // Lấy orderId từ request
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            Order order = dao.getOrderByOrderId(orderId);
            String orderHash = order.getOrder_hash();
            String orderSignature = order.getOrder_signature();
            
    
            // Khởi tạo đối tượng Signature và thực hiện kiểm tra chữ ký
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(publicKey);
            sig.update(orderHash.getBytes("UTF-8"));
            
            boolean isVerified = sig.verify(Base64.getDecoder().decode(orderSignature));
            if (isVerified) {
            	 dao.updateOrderVerificationStatus(orderId, true);
            	 resp.sendRedirect("orderdetails?ordeid=" + orderId);
            } else {
           	 resp.sendRedirect("orderdetails?ordeid=" + orderId);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
            resp.getWriter().write("Fail");
        }
    }
}


