package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Product;

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
					return new Product(
						rs.getInt(1),    // ID sản phẩm
						rs.getString(2), // Tên sản phẩm
						rs.getString(3), // Mô tả
						rs.getDouble(4), // Giá
						rs.getString(5), // Loại
						rs.getString(6), // Hình ảnh
						rs.getString(7), // Thương hiệu
						rs.getInt(8),    // Số lượng
						rs.getInt(9)     // Trạng thái
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
}
