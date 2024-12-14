package models;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class HashSignatureService {
    private static final String BASE_URL = "http://localhost:8080/PhuKienThuCung/";

    // Tải hash từ hệ thống
    public String fetchHash(int orderId) throws Exception {
        String apiUrl = BASE_URL + "get-order-hash?orderId=" + orderId;
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");

        // Kiểm tra mã trạng thái HTTP
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Failed to fetch order hash. HTTP response code: " + responseCode);
        }

        // Đọc nội dung phản hồi từ server
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    // Ký hash với private key
    public String signHashWithPrivateKey(String hash, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(hash.getBytes("UTF-8"));
        byte[] signedData = signature.sign();
        return Base64.getEncoder().encodeToString(signedData);
    }

    // Tải private key từ file (dạng Base64)
    public PrivateKey loadPrivateKey(String privateKeyPath) throws Exception {
        // Sử dụng BufferedReader để đọc dữ liệu dạng văn bản (Base64 encoded)
        BufferedReader reader = new BufferedReader(new FileReader(privateKeyPath));

        // Đọc dữ liệu từ file (mã hóa Base64)
        String privateKeyStr = reader.readLine(); // Đọc toàn bộ chuỗi Base64
        reader.close(); // Đóng BufferedReader

        // Giải mã Base64 thành mảng byte
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyStr);

        // Tạo đối tượng KeyFactory cho RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        // Chuyển đổi mảng byte thành đối tượng PrivateKey
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return keyFactory.generatePrivate(keySpec);
    }

    // Tải chữ ký lên hệ thống
    public String uploadSignature(int orderId, String signature) throws Exception {
    	
        String apiUrl = BASE_URL + "upload-signature";
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        String encodedSignature = URLEncoder.encode(signature, "UTF-8");
        String postData = "orderId=" + orderId + "&signature=" + encodedSignature;
        try (OutputStream os = connection.getOutputStream()) {
            os.write(postData.getBytes());
            os.flush();
        }

        if (connection.getResponseCode() == 200) {
            return "Tải lên thành công!";
        } else {
            return "Lỗi: " + connection.getResponseMessage();
        }
    }
}
