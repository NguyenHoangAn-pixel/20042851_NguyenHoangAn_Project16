package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;
import models.OrderItem;
import repositories.OrderRepository;
import services.OrderService;

@WebServlet("/orders")
public class OrderController extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        // Initialize the service (typically with dependency injection)
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        if (connection == null) {
            throw new ServletException("Database connection is not available.");
        }
        this.orderService = new OrderService(new OrderRepository(connection));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String orderId = req.getParameter("orderId");

        if (action == null) {
            // Default action: List all orders
            List<Order> orders = orderService.getAllOrders();
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("/views/order_list.jsp").forward(req, resp);
        } else if (action.equals("view") && orderId != null) {
            // View details of a specific order
            try {
                Order order = orderService.getOrderById(Integer.parseInt(orderId));
                req.setAttribute("order", order);
                req.setAttribute("orderItems", orderService.getOrderItemsByOrderId(Integer.parseInt(orderId)));
                req.getRequestDispatcher("/views/order_details.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID format.");
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving order details.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action or missing order ID.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String orderId = req.getParameter("orderId");
        String itemId = req.getParameter("itemId");
        String quantity = req.getParameter("quantity");

        if ("updateQuantity".equals(action) && orderId != null && itemId != null && quantity != null) {
            try {
                int newQuantity = Integer.parseInt(quantity);
                orderService.updateOrderItemQuantity(Integer.parseInt(orderId), Integer.parseInt(itemId), newQuantity);
                resp.sendRedirect(req.getContextPath() + "/orders?action=view&orderId=" + orderId);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quantity format.");
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating order item quantity.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters for updating quantity.");
        }
    }
}
