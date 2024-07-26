package models;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String imageUrl;
    private Category category;

    public Product(int i, String name2, double price2, String description2, int stock2, String string) {
    }

    public Product(String name, double price, String description, int stock, String imageUrl, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Product(int id, String name, double price, String description, int stock, String imageUrl, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

	public void setProductName(String name2) {
		// TODO Auto-generated method stub
		
	}

	public void setCategoryID(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setProductID(int productId) {
		// TODO Auto-generated method stub
		
	}

	public String getProductName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCategoryID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getProductID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setImagePath(String string) {
		// TODO Auto-generated method stub
		
	}

	public String getImagePath() {
		// TODO Auto-generated method stub
		return null;
	}
}
