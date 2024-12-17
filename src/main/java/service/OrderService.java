package service;

import dao.KeyDAO;
import dao.OrderDAO;
import models.Order;
import models.OrderDetail;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private KeyDAO keyDAO = new KeyDAO();

    public String generateOrderHash(int orderId) throws Exception {
        try {
            Order order = orderDAO.getOrderById(orderId);
            if (order == null) {
                return null;
            }
            List<OrderDetail> orderDetailList = orderDAO.getOrderDetailsByOrderId(orderId);

            // 3. Xây dựng chuỗi dữ liệu để hash
            StringBuilder orderData = new StringBuilder();
            orderData.append(order.getOrder_id())
                    .append(order.getUser_id())
                    .append(order.getName())
                    .append(order.getEmail_address())
                    .append(order.getPhone())
                    .append(order.getTotal_price())
                    .append(order.getShipping_address());

            for (OrderDetail orderDetail : orderDetailList) {
                orderData.append(orderDetail.getProduct_id())
                        .append(orderDetail.getQuantity())
                        .append(orderDetail.getPrice());
            }

            return HashService.generateHash(orderData.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
//    public boolean updateSignature(int orderId, String signature) throws Exception {
//        String orderHash = generateOrderHash(orderId); // Tạo lại hash từ đơn hàng
//
//    }
    public boolean verifySignature(int orderId, String publickey, String signature) throws Exception {
        String orderHash = generateOrderHash(orderId); // Tạo lại hash từ đơn hàng
        byte[] keyBytes = Base64.getDecoder().decode(publickey);
        // Tạo đối tượng X509EncodedKeySpec từ mảng byte
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // Sử dụng KeyFactory để tạo PublicKey từ keySpec
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return VerifyService.verifySignature(orderHash, signature, publicKey);
    }

}