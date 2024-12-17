package service;

import java.security.MessageDigest;
import java.util.Base64;


// Hash đơn hàng
public class HashService {
    public static String generateHash(String orderData) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(orderData.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hashBytes);
    }
}
