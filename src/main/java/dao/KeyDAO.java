package dao;

import java.security.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// Tạo key
public class KeyDAO {
    static JDBCUtil jdbcUtil = new JDBCUtil();
    public static String getPublicKeyByUserId(int userId) throws ClassNotFoundException, SQLException {
        String select = "SELECT public_key FROM `user_keys` WHERE user_id = ? and end_time is null"; // Truy vấn lấy publickey theo iduser

        try (Connection connection = jdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setInt(1, userId); // Gán iduser vào câu truy vấn
            ResultSet rs = preparedStatement.executeQuery();

            // Nếu tìm thấy publickey, trả về giá trị publickey
            if (rs.next()) {
                return rs.getString("public_key").trim();
            }
        }

        // Nếu không tìm thấy publickey, trả về null
        return null;
    }




    public static void savePublicKeyToDatabase(int userId, String publicKey) throws Exception {
        String query = "INSERT INTO user_keys (user_id, public_key, status) VALUES (?, ?, ?)";
        try (Connection conn = jdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, publicKey);
            stmt.setString(3, "Created");
            stmt.executeUpdate();
        }
    }
    public static void updateEndTimeForOldKeys(int userId) throws Exception {
        String query = "UPDATE user_keys SET end_time = NOW(), status = 'Updated' WHERE user_id = ? AND end_time IS NULL";
        try (Connection conn = jdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
    public static void reportLostKey(int userId) throws Exception {
        String updateQuery = "UPDATE user_keys SET end_time = NOW(), status = 'Reported' WHERE user_id = ? AND end_time IS NULL";
        try (Connection conn = jdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
}
