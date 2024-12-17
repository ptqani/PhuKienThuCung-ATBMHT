package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    // Phương thức kết nối riêng, dùng để kết nối tới cơ sở dữ liệu
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // Tải driver MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Kết nối tới cơ sở dữ liệu và trả về đối tượng Connection
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_thu_cung", "root", "123456");
    }
}
