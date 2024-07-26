package models;

import java.util.List;

public class Order {
    private int id;
    private User user;
    private double total;
    private String status;
    private List<OrderItem> items;
	public int getId() {
		return id;
	}
	public void setId(long l) {
		this.id = (int) l;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public long getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}

    // getters and setters
}
