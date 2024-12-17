package dao;

import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    static JDBCUtil jdbcUtil = new JDBCUtil();
    // Phương thức lấy một sản phẩm theo ID
    public Product getProductById(String itemId) throws ClassNotFoundException, SQLException {
        Product product = null;
        String select = "SELECT * FROM product WHERE id = ?"; // Truy vấn lấy sản phẩm theo ID

        // Kết nối và thực thi truy vấn
        try (Connection connection = jdbcUtil.getConnection();
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
        try (Connection connection = jdbcUtil.getConnection();
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
    // Phương thức lấy một sản phẩm theo ID
    public Product getProductID(String id) throws ClassNotFoundException, SQLException {
        String select = "SELECT * FROM product WHERE id = ?";

        // Kết nối và thực thi truy vấn
        try (Connection connection = jdbcUtil.getConnection();
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
        try (Connection connection = jdbcUtil.getConnection();
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
        try (Connection connection = jdbcUtil.getConnection();
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
        try (Connection connection = jdbcUtil.getConnection();
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
        try (Connection connection = jdbcUtil.getConnection();
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

        try (Connection connection = jdbcUtil.getConnection();
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

        try (Connection connection = jdbcUtil.getConnection();
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

        try (Connection connection = jdbcUtil.getConnection();
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
        try (Connection connection = jdbcUtil.getConnection();
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
