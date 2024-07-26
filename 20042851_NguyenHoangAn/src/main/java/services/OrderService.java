package services;

import models.Order;
import repositories.OrderRepository;

import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int i) {
        return orderRepository.findById(i);
    }

	public void updateOrderItemQuantity(int int1, int int2, int newQuantity) {
		// TODO Auto-generated method stub
		
	}

	public Object getOrderItemsByOrderId(int int1) {
		// TODO Auto-generated method stub
		return null;
	}

    // Other business logic
}
