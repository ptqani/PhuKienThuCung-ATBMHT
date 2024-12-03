
package dao;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Product;
import models.User;

public class Database {

	// Phương thức kết nối riêng, dùng để kết nối tới cơ sở dữ liệu
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// Tải driver MySQL
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Kết nối tới cơ sở dữ liệu và trả về đối tượng Connection
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_thu_cung", "root", "123456");
	}

	// Phương thức lấy một sản phẩm theo ID
	public Product getProductID(String id) throws ClassNotFoundException, SQLException {
		String select = "SELECT * FROM product WHERE id = ?";

		// Kết nối và thực thi truy vấn
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select)) {

			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			// Nếu tìm thấy sản phẩm, trả về đối tượng Product
			if (rs.next()) {
				return new Product(rs.getInt(1), // ID sản phẩm
						rs.getString(2), // Tên sản phẩm
						rs.getString(3), // Mô tả
						rs.getDouble(4), // Giá
						rs.getString(5), // Loại
						rs.getString(6), // Hình ảnh
						rs.getString(7), // Thương hiệu
						rs.getInt(8), // Số lượng
						rs.getInt(9) // Trạng thái
				);
			}
		}
		// Nếu không tìm thấy sản phẩm, trả về null
		return null;
	}

	// Phương thức lấy 3 sản phẩm ngẫu nhiên từ cơ sở dữ liệu
	public List<Product> getRandomProducts() throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		String select = "SELECT * FROM product ORDER BY RAND() LIMIT 3";

		// Kết nối và thực thi truy vấn
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select)) {

			ResultSet rs = preparedStatement.executeQuery();

			// Duyệt qua kết quả và thêm vào danh sách sản phẩm
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), // ID sản phẩm
						rs.getString(2), // Tên sản phẩm
						rs.getString(3), // Mô tả
						rs.getDouble(4), // Giá
						rs.getString(5), // Loại
						rs.getString(6), // Hình ảnh
						rs.getString(7), // Thương hiệu
						rs.getInt(8), // Số lượng
						rs.getInt(9) // Trạng thái
				));
			}
		}
		return list;
	}

	// Phương thức lấy 6 sản phẩm mới nhất từ cơ sở dữ liệu
	public List<Product> getLatestProducts() throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		String select = "SELECT * FROM product ORDER BY id DESC LIMIT 6";

		// Kết nối và thực thi truy vấn
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select)) {

			ResultSet rs = preparedStatement.executeQuery();

			// Duyệt qua kết quả và thêm vào danh sách sản phẩm
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), // ID sản phẩm
						rs.getString(2), // Tên sản phẩm
						rs.getString(3), // Mô tả
						rs.getDouble(4), // Giá
						rs.getString(5), // Loại
						rs.getString(6), // Hình ảnh
						rs.getString(7), // Thương hiệu
						rs.getInt(8), // Số lượng
						rs.getInt(9) // Trạng thái
				));
			}
		}
		return list;
	}
	public boolean registerUser(String username, String pass, String email, String phone)
	        throws ClassNotFoundException, SQLException {
	    String insert = "INSERT INTO user (username, email, phone, password, role, created_at, updated_at) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

	    // Kết nối và thực thi truy vấn
	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(insert)) {

	        // Gán giá trị cho các tham số trong câu lệnh SQL
	        preparedStatement.setString(1, username);  // Set username
	        preparedStatement.setString(2, email);     // Set email
	        preparedStatement.setString(3, phone);     // Set phone
	        preparedStatement.setString(4, pass);      // Set password (nên mã hóa mật khẩu trước khi lưu)
	        preparedStatement.setString(5, "user");    // Set role mặc định là 'user'

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

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            
            preparedStatement.setString(1, email);   // Set email
            preparedStatement.setString(2, password); // Set password

            ResultSet rs = preparedStatement.executeQuery();

            // Nếu tìm thấy người dùng, trả về đối tượng User
            if (rs.next()) {
                return new User(
                    rs.getInt("id"), 
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("role")
                );
            }
        }
        // Nếu không tìm thấy người dùng hoặc mật khẩu sai
        return null;
    }

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		String username = "123";
//		String password = "123";
//		String email = "email";
//		String phone = "1235484841";
//		Database dao = new Database();
//        boolean success = dao.registerUser(username, password, email,phone);
//        System.out.println(success);
//		   String email = "email";  // Thay đổi email kiểm tra
//	        String password = "123"; // Thay đổi mật khẩu kiểm tra
//
//	        Database dao = new Database();
//	        try {
//	            User user = dao.loginUser(email, password);
//	            if (user != null) {
//	                System.out.println("Đăng nhập thành công!");
//	                System.out.println("Thông tin người dùng: " + user);
//	            } else {
//	                System.out.println("Đăng nhập thất bại. Sai email hoặc mật khẩu.");
//	            }
//	        } catch (SQLException | ClassNotFoundException e) {
//	            e.printStackTrace();
//	        }
//	    
	}
}
