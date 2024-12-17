package dao;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    static JDBCUtil jdbcUtil = new JDBCUtil();
    public boolean registerUser(String username, String pass, String email, String phone)
            throws ClassNotFoundException, SQLException {
        String insert = "INSERT INTO user (username, email, phone, password, role, created_at) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        // Kết nối và thực thi truy vấn
        try (Connection connection = jdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {

            // Gán giá trị cho các tham số trong câu lệnh SQL
            preparedStatement.setString(1, username); // Set username
            preparedStatement.setString(2, email); // Set email
            preparedStatement.setString(3, phone); // Set phone
            preparedStatement.setString(4, pass); // Set password (nên mã hóa mật khẩu trước khi lưu)
            preparedStatement.setString(5, "user"); // Set role mặc định là 'user'

            // Thực thi câu lệnh để chèn dữ liệu vào cơ sở dữ liệu
            int result = preparedStatement.executeUpdate();

            // Nếu có ít nhất một dòng bị ảnh hưởng, nghĩa là đăng ký thành công
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Đăng ký không thành công: " + e.getMessage());
        }
    }

    // Phương thức để đăng nhập người dùng
    public User loginUser(String email, String password) throws ClassNotFoundException, SQLException {
        String select = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = jdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setString(1, email); // Set email
            preparedStatement.setString(2, password); // Set password

            ResultSet rs = preparedStatement.executeQuery();

            // Nếu tìm thấy người dùng, trả về đối tượng User
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                        rs.getString("password"), rs.getString("phone"), rs.getString("role"));
            }
        }
        // Nếu không tìm thấy người dùng hoặc mật khẩu sai
        return null;
    }
}
