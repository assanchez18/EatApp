package es.restaurant.EatApp.models;

public class Product {

	private int id;
	private String name;
	private String description;
	private double price;
	private ProductPriority priority;
	
	public Product(int id, String name, String description, double price, int priority) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.priority = new ProductPriority(priority);
	}
	
	public Product(int id, String name, String description, double price, ProductPriority priority) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.priority = priority;
	}

	public int getId() {
		return this.id;
	}
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setName(String n) {
		this.name = n;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public ProductPriority getPriority() {
		return this.priority;
	}
	
	public void setPriority(ProductPriority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", priority=" + priority + "]";
	}
	
	public boolean equals(Product u) {
		return (this.name.compareTo(u.getName())== 0 
				&& this.description.compareTo(u.getDescription())== 0)
				&& this.price == u.getPrice()
				&& this.priority.equals(u.getPriority());
	}
	
}