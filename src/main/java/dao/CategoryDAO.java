package dao;

import models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    static JDBCUtil jdbcUtil = new JDBCUtil();
    // Phương thức lấy tất cả danh mục từ cơ sở dữ liệu
    public List<Category> getAllCategories() throws ClassNotFoundException, SQLException {
        List<Category> categories = new ArrayList<>();
        String select = "SELECT * FROM category"; // Truy vấn lấy tất cả danh mục

        // Kết nối và thực thi truy vấn
        try (Connection connection = jdbcUtil.getConnection();
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
}
