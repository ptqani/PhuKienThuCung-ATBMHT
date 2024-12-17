package controllers.order;

import dao.JDBCUtil;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

public class OrderVerification {
     static JDBCUtil jdbcUtil = new JDBCUtil();

    // Lấy public key từ bảng user_keys
    public static PublicKey getPublicKey(int userId) throws Exception {
        String sql = "SELECT public_key FROM user_keys WHERE user_id = ? AND (end_time IS NULL OR end_time > NOW())";
        try (Connection conn = jdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String publicKeyStr = rs.getString("public_key");
                byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
                return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes));
            }
        }
        throw new Exception("Public key not found or expired for user ID: " + userId);
    }

    // Xác minh chữ ký đơn hàng
    public static boolean verifyOrder(int orderId) throws Exception {
        String sql = "SELECT o.order_data, o.signature, o.user_id FROM orders o WHERE o.id = ?";
        try (Connection conn = jdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String orderData = rs.getString("order_data");
                String signature = rs.getString("signature");
                int userId = rs.getInt("user_id");

                PublicKey publicKey = getPublicKey(userId);
                return verifySignature(orderData, signature, publicKey);
            }
        }
        return false;
    }

    // Hàm xác minh chữ ký
    private static boolean verifySignature(String data, String signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(data.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return sig.verify(signatureBytes);
    }

    public static void main(String[] args) {
        try {
            int orderId = 1; // ID của đơn hàng cần xác minh
            boolean isValid = verifyOrder(orderId);
            System.out.println("Order verification: " + (isValid ? "Valid" : "Invalid"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

