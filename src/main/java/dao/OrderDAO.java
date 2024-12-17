package dao;

import models.Cart;
import models.LineItem;
import models.Order;
import models.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    static JDBCUtil jdbcUtil = new JDBCUtil();
    public List<Order> getOrdersByUserId(int userId) throws SQLException, ClassNotFoundException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";

        try (Connection connection = jdbcUtil.getConnection();
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
                        resultSet.getString("order_signature"));
                orders.add(order);
            }
        }

        return orders;
    }

    public String getOrderSignature(int orderId) throws ClassNotFoundException, SQLException {
        String select = "SELECT order_signature FROM `orders` WHERE order_id = ?"; // Truy vấn lấy publickey theo iduser

        try (Connection connection = jdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {

            preparedStatement.setInt(1, orderId); // Gán iduser vào câu truy vấn
            ResultSet rs = preparedStatement.executeQuery();

            // Nếu tìm thấy publickey, trả về giá trị publickey
            if (rs.next()) {
                return rs.getString("order_signature").trim();
            }
        }

        // Nếu không tìm thấy publickey, trả về null
        return null;
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
            connection = jdbcUtil.getConnection();

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

        try (Connection connection = jdbcUtil.getConnection();
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

        try (Connection connection = jdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, orderId); // Thêm orderId vào câu truy vấn

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = new Order(resultSet.getInt("order_id"), resultSet.getInt("user_id"),
                        resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("email_address"),
                        resultSet.getString("created_at"), resultSet.getInt("total_price"),
                        resultSet.getString("shipping_address"), resultSet.getString("status"), resultSet.getString("order_signature"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Không thể lấy thông tin đơn hàng: " + e.getMessage());
        }

        return order;
    }

    public boolean saveOrderDetails(int orderId, Cart cart) throws ClassNotFoundException, SQLException {
        String insertQuery = "INSERT INTO order_detail (order_id, product_id, product_name, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = jdbcUtil.getConnection();
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
        try (Connection connection = jdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hash);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        }
    }

    public int createOrder(int userId, String name, String phone, String emailAddress, int totalPrice,
                           String shippingAddress, String status) throws ClassNotFoundException, SQLException {
        String insertQuery = "INSERT INTO `orders` (user_id, name, phone, email_address, total_price, shipping_address, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = jdbcUtil.getConnection();
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
}
