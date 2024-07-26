package models;


public class OrderItem {
    private int id;
    private Order order;
    private Product product;
    private int quantity;
    private double price;
	public int getId() {
		return id;
	}
	public void setId(long l) {
		this.id = (int) l;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
    
    
    // getters and setters
}
