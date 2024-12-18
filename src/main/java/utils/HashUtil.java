package utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String hashOrderData(int userId, String name, String phone, String email, String address, int totalCurrencyInt) {
        // Chuỗi cần băm
        String dataToHash = userId + "|" + name + "|" + phone + "|" + email + "|" + address + "|" + totalCurrencyInt;

        try {
            // Sử dụng SHA-256 để băm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataToHash.getBytes("UTF-8"));

            // Chuyển mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
