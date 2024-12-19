
package dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import models.Cart;
import models.Category;
import models.LineItem;
import models.Order;
import models.OrderDetail;
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
	// Phương thức lấy thông tin order_hash, order_signature, user_id theo order_id
    public Order getOrderByOrderId(int orderId) throws SQLException, ClassNotFoundException {
        String query = "SELECT order_hash, order_signature, user_id FROM orders WHERE order_id = ?";
        
        try (Connection connection = getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, orderId);  // Gán order_id vào câu truy vấn
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	int user_id = resultSet.getInt("user_id");
                    String order_hash = resultSet.getString("order_hash");
                    String order_signature = resultSet.getString("order_signature");
                 
                    
                    // Trả về đối tượng Order chứa thông tin
                    return new Order(user_id,order_hash,order_signature);
                } else {
                    return null;  // Không tìm thấy đơn hàng
                }
            }
        }
    }
	public List<Order> getOrdersByUserId(int userId) throws SQLException, ClassNotFoundException {
		List<Order> orders = new ArrayList<>();
		String query = "SELECT * FROM orders WHERE user_id = ?";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			// Gán giá trị cho tham số user_id
			preparedStatement.setInt(1, userId);

			// Thực thi truy vấn
			ResultSet resultSet = preparedStatement.executeQuery();

			// Duyệt qua kết quả và tạo các đối tượng Order
			while (resultSet.next()) {
				Order order = new Order(resultSet.getInt("order_id"), resultSet.getInt("user_id"),
						resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("email_address"),
						resultSet.getString("order_date"), resultSet.getInt("total_price"),
						resultSet.getString("shipping_address"), resultSet.getString("status"),
						resultSet.getString("order_hash"), resultSet.getString("order_signature"),resultSet.getInt("is_verified"));
				orders.add(order);
			}
		}

		return orders;
	}
	public boolean updateOrderVerificationStatus(int orderId, boolean isVerified) throws SQLException, ClassNotFoundException {
	    // Khởi tạo kết nối
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    String updateSQL = "UPDATE orders SET is_verified = ? WHERE order_id = ?";

	    try {
	        // Tạo kết nối tới cơ sở dữ liệu
	        connection = getConnection();

	        // Chuẩn bị câu lệnh SQL
	        preparedStatement = connection.prepareStatement(updateSQL);
	        preparedStatement.setInt(1, isVerified ? 1 : 0); // Gán giá trị is_verified (1 nếu verified, 0 nếu chưa verified)
	        preparedStatement.setInt(2, orderId); // Gán giá trị order_id

	        // Thực thi lệnh cập nhật
	        int rowsAffected = preparedStatement.executeUpdate();

	        // Trả về kết quả (true nếu cập nhật thành công)
	        return rowsAffected > 0;
	    } finally {
	        // Đóng tài nguyên
	        if (preparedStatement != null)
	            preparedStatement.close();
	        if (connection != null)
	            connection.close();
	    }
	}

	// Phương thức cập nhật chữ ký theo order_id
	public boolean updateOrderSignature(int orderId, String orderSignature)
			throws SQLException, ClassNotFoundException {
		// Khởi tạo kết nối
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String updateSQL = "UPDATE orders SET order_signature = ? WHERE order_id = ?";
		try {
			// Tạo kết nối tới cơ sở dữ liệu
			connection = getConnection();

			// Chuẩn bị câu lệnh SQL
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, orderSignature); // Gán giá trị chữ ký
			preparedStatement.setInt(2, orderId); // Gán giá trị order_id

			// Thực thi lệnh cập nhật
			int rowsAffected = preparedStatement.executeUpdate();

			// Trả về kết quả (true nếu cập nhật thành công)
			return rowsAffected > 0;
		} finally {
			// Đóng tài nguyên
			if (preparedStatement != null)
				preparedStatement.close();
			if (connection != null)
				connection.close();
		}
	}

	// Phương thức lấy chi tiết đơn hàng theo order_id
	public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException, ClassNotFoundException {
		List<OrderDetail> orderDetails = new ArrayList<>();

		String query = "SELECT * FROM order_detail WHERE order_id = ?";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			// Gán giá trị cho tham số order_id
			preparedStatement.setInt(1, orderId);

			// Thực thi truy vấn
			ResultSet resultSet = preparedStatement.executeQuery();

			// Duyệt qua kết quả và tạo các đối tượng OrderDetail
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int orderIdFromDb = resultSet.getInt("order_id");
				int productId = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				int quantity = resultSet.getInt("quantity");
				int price = resultSet.getInt("price");
				int totalPrice = resultSet.getInt("total_price");

				// Tạo đối tượng OrderDetail và thêm vào danh sách
				OrderDetail orderDetail = new OrderDetail(id, orderIdFromDb, productId, productName, quantity, price,
						totalPrice);
				orderDetails.add(orderDetail);
			}
		}

		return orderDetails;
	}

	public Order getOrderById(int orderId) throws SQLException, ClassNotFoundException {
		Order order = null;
		String query = "SELECT * FROM orders WHERE order_id = ?";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, orderId); // Thêm orderId vào câu truy vấn

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				order = new Order(resultSet.getInt("order_id"), resultSet.getInt("user_id"),
						resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("email_address"),
						resultSet.getString("order_date"), resultSet.getInt("total_price"),
						resultSet.getString("shipping_address"), resultSet.getString("status"),
						resultSet.getString("order_hash"), resultSet.getString("order_signature"), resultSet.getInt("is_verified"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Không thể lấy thông tin đơn hàng: " + e.getMessage());
		}

		return order;
	}

	public boolean saveOrderDetails(int orderId, Cart cart) throws ClassNotFoundException, SQLException {
		String insertQuery = "INSERT INTO order_detail (order_id, product_id, product_name, quantity, price) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

			// Lặp qua các mục trong giỏ hàng
			for (LineItem item : cart.getItems()) {
				preparedStatement.setInt(1, orderId); // ID đơn hàng
				preparedStatement.setInt(2, item.getProduct().getId()); // ID sản phẩm
				preparedStatement.setString(3, item.getProduct().getName()); // Tên sản phẩm
				preparedStatement.setInt(4, item.getQuantity()); // Số lượng
				preparedStatement.setDouble(5, item.getProduct().getPrice()); // Giá tại thời điểm mua

				preparedStatement.addBatch(); // Thêm vào batch
			}

			// Thực thi batch
			int[] rowsAffected = preparedStatement.executeBatch();

			// Kiểm tra nếu tất cả các mục được lưu thành công
			for (int rows : rowsAffected) {
				if (rows == 0) {
					return false;
				}
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Không thể lưu chi tiết đơn hàng: " + e.getMessage());
		}
	}

	public void updateOrderHash(int orderId, String hash) throws SQLException, ClassNotFoundException {
		String query = "UPDATE orders SET order_hash = ? WHERE order_id = ?";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, hash);
			preparedStatement.setInt(2, orderId);
			preparedStatement.executeUpdate();
		}
	}

	public int createOrder(int userId, String name, String phone, String emailAddress, int totalPrice,
			String shippingAddress, String status, String orderHash) throws ClassNotFoundException, SQLException {
		String insertQuery = "INSERT INTO `orders` (user_id, name, phone, email_address, total_price, shipping_address, status, order_hash) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

			// Set các tham số vào câu lệnh SQL
			preparedStatement.setInt(1, userId); // user_id
			preparedStatement.setString(2, name); // name
			preparedStatement.setString(3, phone); // phone
			preparedStatement.setString(4, emailAddress); // email_address
			preparedStatement.setInt(5, totalPrice); // total_price
			preparedStatement.setString(6, shippingAddress); // shipping_address
			preparedStatement.setString(7, status); // status
			preparedStatement.setString(8, orderHash); // order_hash

			// Thực thi câu lệnh INSERT vào cơ sở dữ liệu
			int rowsAffected = preparedStatement.executeUpdate();

			// Kiểm tra nếu ít nhất 1 dòng bị ảnh hưởng, nghĩa là tạo đơn hàng thành công
			if (rowsAffected > 0) {
				// Lấy khóa tự động sinh (Generated Key)
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					return rs.getInt(1); // Trả về ID của đơn hàng vừa được tạo
				}
			}
			return -1; // Trả về -1 nếu không có kết quả
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Không thể tạo đơn hàng: " + e.getMessage());
		}
	}
	public PublicKey PublicKeyByUserId(int userId) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
	    String select = "SELECT publickey FROM shop_thu_cung.key WHERE iduser = ? AND (end_time IS NULL OR end_time > NOW())";

	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(select)) {

	        preparedStatement.setInt(1, userId); // Gán iduser vào câu truy vấn
	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            // Lấy publickey dưới dạng chuỗi Base64 từ cơ sở dữ liệu
	            String publicKeyString = rs.getString("publickey");

	            // Giải mã chuỗi Base64 thành byte[]
	            byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);

	            // Chuyển đổi byte[] thành PublicKey
	            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	            PublicKey publicKey = keyFactory.generatePublic(keySpec);

	            return publicKey; // Trả về PublicKey
	        }
	    }

	    return null; // Trả về null nếu không tìm thấy khóa công khai
	}


	
	public String getPublicKeyByUserId(int userId) throws ClassNotFoundException, SQLException {
		String select = "SELECT publickey FROM `key` WHERE iduser = ?"; // Truy vấn lấy publickey theo iduser

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select)) {

			preparedStatement.setInt(1, userId); // Gán iduser vào câu truy vấn
			ResultSet rs = preparedStatement.executeQuery();

			// Nếu tìm thấy publickey, trả về giá trị publickey
			if (rs.next()) {
				return rs.getString("publickey");
			}
		}

		// Nếu không tìm thấy publickey, trả về null
		return null;
	}

	public boolean updatePublicKeyByUserId(int userId, byte[] publicKeyData, String status)
	        throws ClassNotFoundException, SQLException {
	    String updateQuery = "UPDATE `key` SET publickey = ?, updated_day = CURRENT_TIMESTAMP, status = ? WHERE iduser = ?";

	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

	        preparedStatement.setBytes(1, publicKeyData); // Lưu public key dưới dạng byte[]
	        preparedStatement.setString(2, status); // Lưu trạng thái
	        preparedStatement.setInt(3, userId); // Lưu ID người dùng

	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Failed to update public key for user ID " + userId + ": " + e.getMessage());
	    }
	}

	public boolean upsertKey(int userId, String publicKey, String status) throws ClassNotFoundException, SQLException {
		String upsertQuery = "INSERT INTO `key` (iduser, publickey, created_day, status) "
				+ "VALUES (?, ?, CURRENT_TIMESTAMP, ?) "
				+ "ON DUPLICATE KEY UPDATE publickey = ?, status = ?";  // Không sử dụng updated_day

		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(upsertQuery)) {

			// Set các giá trị vào PreparedStatement
			preparedStatement.setInt(1, userId); // Set iduser
			preparedStatement.setString(2, publicKey); // Set publickey
			preparedStatement.setString(3, status); // Set status khi chèn mới

			// Cập nhật giá trị khi đã tồn tại bản ghi
			preparedStatement.setString(4, publicKey); // Set publickey cho cập nhật
			preparedStatement.setString(5, status); // Set status cho cập nhật

			// Thực thi câu lệnh để chèn hoặc cập nhật dữ liệu
			int result = preparedStatement.executeUpdate();

			return result > 0; // Nếu có ít nhất một dòng bị ảnh hưởng, trả về true
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Cập nhật hoặc chèn khóa không thành công: " + e.getMessage());
		}
	}
	// Phương thức lấy một sản phẩm theo ID
	public Product getProductById(String itemId) throws ClassNotFoundException, SQLException {
		Product product = null;
		String select = "SELECT * FROM product WHERE id = ?"; // Truy vấn lấy sản phẩm theo ID

		// Kết nối và thực thi truy vấn
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select)) {

			preparedStatement.setString(1, itemId); // Gán ID sản phẩm vào câu truy vấn
			ResultSet rs = preparedStatement.executeQuery();

			// Nếu tìm thấy sản phẩm, trả về đối tượng Product
			if (rs.next()) {
				product = new Product(rs.getInt(1), // ID sản phẩm
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

		return product; // Trả về sản phẩm nếu tìm thấy, nếu không trả về null
	}

	// Phương thức lấy tất cả sản phẩm theo danh mục
	public List<Product> getProductsByCategory(int categoryId) throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		String select = "SELECT * FROM product WHERE cateID = ?"; // Truy vấn lấy sản phẩm theo danh mục

		// Kết nối và thực thi truy vấn
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select)) {

			preparedStatement.setInt(1, categoryId); // Gán categoryId vào câu truy vấn
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

	// Phương thức lấy tất cả danh mục từ cơ sở dữ liệu
	public List<Category> getAllCategories() throws ClassNotFoundException, SQLException {
		List<Category> categories = new ArrayList<>();
		String select = "SELECT * FROM category"; // Truy vấn lấy tất cả danh mục

		// Kết nối và thực thi truy vấn
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select)) {

			ResultSet rs = preparedStatement.executeQuery();

			// Duyệt qua kết quả và thêm vào danh sách các danh mục
			while (rs.next()) {
				categories.add(new Category(rs.getInt(1), // ID danh mục
						rs.getString(2) // Tên danh mục
				));
			}
		}

		return categories;
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

	// Phương thức lấy tất cả sản phẩm từ cơ sở dữ liệu
	public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		String select = "SELECT * FROM product"; // Truy vấn lấy tất cả sản phẩm

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

	// Phương thức lấy 8 sản phẩm có giá bán thấp nhất
	public List<Product> getEightCheapestProducts() throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		String select = "SELECT * FROM product ORDER BY price ASC LIMIT 8"; // Lấy 8 sản phẩm có giá thấp nhất

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

	// Phương thức lấy 6 sản phẩm ngẫu nhiên từ cơ sở dữ liệu
	public List<Product> getRandomSixProducts() throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		String select = "SELECT * FROM product ORDER BY RAND() LIMIT 6";

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

	// Phương thức lấy 3 sản phẩm cũ nhất từ cơ sở dữ liệu
	public List<Product> getOldThreeProducts() throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		String select = "SELECT * FROM product ORDER BY id ASC LIMIT 3"; // Lấy 3 sản phẩm cũ nhất theo ID tăng dần

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

	// Phương thức lấy 3 sản phẩm mới nhất từ cơ sở dữ liệu
	public List<Product> getLatestThreeProducts() throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		String select = "SELECT * FROM product ORDER BY id DESC LIMIT 3"; // Lấy 3 sản phẩm mới nhất theo ID giảm dần

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

	// Phương thức lấy 6 sản phẩm cũ nhất từ cơ sở dữ liệu
	public List<Product> getOldSixProducts() throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		String select = "SELECT * FROM product ORDER BY id ASC LIMIT 6"; // Lấy 6 sản phẩm cũ nhất theo ID tăng dần

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
		String insert = "INSERT INTO user (username, email, phone, password, role, created_at) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

		// Kết nối và thực thi truy vấn
		try (Connection connection = getConnection();
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

		try (Connection connection = getConnection();
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
	public void updateOrderStatus(int orderId, String status) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
		try (Connection conn = getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			// Gán giá trị cho các tham số trong câu lệnh SQL
			stmt.setString(1, status);   // Tham số đầu tiên là trạng thái
			stmt.setInt(2, orderId);     // Tham số thứ hai là mã đơn hàng
			stmt.executeUpdate();        // Thực thi câu lệnh cập nhật
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
	
//	    // Khởi tạo các đối tượng mô phỏng
//        Database dao = new Database();
//        int userId = 2; // Thay đổi ID người dùng cần kiểm tra
//        int orderId = 10; // Thay đổi ID đơn hàng cần kiểm tra
//        
//   
//            // Lấy public key từ cơ sở dữ liệu
//            PublicKey publicKey = dao.PublicKeyByUserId(userId);
//
//            if (publicKey != null) {
//                System.out.println("Public Key đã được lấy thành công.");
//                System.out.println("Public Key:" + publicKey);
//            } else {
//                System.out.println("Không tìm thấy Public Key cho userId = " + userId);
//                return;
//            }
//    
//            // Lấy thông tin đơn hàng từ cơ sở dữ liệu
//            Order order = dao.getOrderByOrderId(orderId);
//            if (order == null) {
//                System.out.println("Không tìm thấy đơn hàng với orderId = " + orderId);
//                return;
//            }
//
//            // Lấy order_hash và order_signature
//            String orderHash = order.getOrder_hash();
//            String orderSignature = order.getOrder_signature();
//            
//            System.out.println("Order Hash: " + orderHash);
//            System.out.println("Order Signature: " + orderSignature);
//
//            // Kiểm tra chữ ký
//            Signature sig = Signature.getInstance("SHA256withRSA");
//            sig.initVerify(publicKey);
//            sig.update(orderHash.getBytes("UTF-8"));
//            
//            boolean isVerified = sig.verify(Base64.getDecoder().decode(orderSignature));
//            if (isVerified) {
//                System.out.println("Verification successful.");
//            } else {
//                System.out.println("Verification failed.");
//            }
//            
//        
    }

}
