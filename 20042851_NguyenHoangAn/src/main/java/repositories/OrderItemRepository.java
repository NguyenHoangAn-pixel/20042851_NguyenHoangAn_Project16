package repositories;


import models.OrderItem;
import models.Order;
import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {
    private Connection connection;

    public OrderItemRepository(Connection connection) {
        this.connection = connection;
    }

    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM order_items");
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getLong("id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getDouble("price"));
                // Set order and product here
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    public OrderItem findById(Long id) {
        OrderItem orderItem = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM order_items WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                orderItem = new OrderItem();
                orderItem.setId(rs.getLong("id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getDouble("price"));
                // Set order and product here
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    public List<OrderItem> findByOrder(Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM order_items WHERE order_id = ?");
            stmt.setLong(1, order.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getLong("id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getDouble("price"));
                // Set order and product here
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    public void save(OrderItem orderItem) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)");
            stmt.setLong(1, orderItem.getOrder().getId());
            stmt.setLong(2, orderItem.getProduct().getId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setDouble(4, orderItem.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Other CRUD operations
}
