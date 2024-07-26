package repositories;

import models.Order;
import models.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("id"));
                order.setId(rs.getLong("user_id")); // assuming user_id is a field
                order.setTotal(rs.getDouble("total_amount")); // assuming total_amount is a field
                order.setStatus(rs.getString("status")); // assuming status is a field
                // Set other properties and relationships
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order findById(long id) {
        Order order = null;
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setId(rs.getLong("id"));
                    order.setId(rs.getLong("user_id")); // assuming user_id is a field
                    order.setTotal(rs.getDouble("total_amount")); // assuming total_amount is a field
                    order.setStatus(rs.getString("status")); // assuming status is a field
                    // Set other properties and relationships
                    
                    // Load order items
                    List<OrderItem> orderItems = getOrderItemsByOrderId(id);
                    order.setItems(orderItems); // assuming Order has a setOrderItems method
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<OrderItem> getOrderItemsByOrderId(long orderId) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setId(rs.getLong("id"));
                    item.setId(rs.getLong("order_id"));
                    item.setPrice(rs.getLong("product_id")); // assuming product_id is a field
                    item.setQuantity(rs.getInt("quantity")); // assuming quantity is a field
                    item.setPrice(rs.getDouble("price")); // assuming price is a field
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Add methods for CRUD operations, e.g., create, update, delete orders
    public void createOrder(Order order) {
        String sql = "INSERT INTO orders (user_id, total_amount, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, order.getUserId());
            stmt.setDouble(2, order.getTotal());
            stmt.setString(3, order.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order) {
        String sql = "UPDATE orders SET total_amount = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, order.getTotal());
            stmt.setString(2, order.getStatus());
            stmt.setLong(3, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(long orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
