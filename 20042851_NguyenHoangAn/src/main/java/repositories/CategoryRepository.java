package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Category;

public class CategoryRepository {
    private Connection connection;

    public CategoryRepository(Connection connection2) {
        try {
            // Khởi tạo kết nối
            this.connection = DriverManager.getConnection(  "jdbc:sqlserver://localhost:1433;databaseName=ToyStore",
                    "sa",
                    "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Process results
            }
        }
        return categories;
    }

   
    public Category findById(int id) {
        Category category = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Categories WHERE CategoryID = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setCategoryName(rs.getString("CategoryName"));
                category.setDescription(rs.getString("Description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public void save(Category category) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Categories (CategoryName, Description) VALUES (?, ?)");
        stmt.setString(1, category.getCategoryName());
        stmt.setString(2, category.getDescription());
        stmt.executeUpdate();
    }

    public void update(Category category) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE Categories SET CategoryName = ?, Description = ? WHERE CategoryID = ?");
        stmt.setString(1, category.getCategoryName());
        stmt.setString(2, category.getDescription());
        stmt.setInt(3, category.getCategoryID());
        stmt.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Categories WHERE CategoryID = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
