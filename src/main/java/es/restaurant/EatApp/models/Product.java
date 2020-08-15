package es.restaurant.EatApp.models;

import es.restaurant.EatApp.models.ProductState.productState;

public class Product {

	private int id;
	private String name;
	private String description;
	private double price;
	private ProductState state;
	private ProductPriority priority;
	
	public Product(int id, String name, String description, double price, int priority) {
		this.id = id;
		this.name = name;
		this.state = new ProductState();
		this.description = description;
		this.price = price;
		this.priority = new ProductPriority(priority);
	}
	
	public Product(int id, String name, String description, double price, ProductPriority priority) {
		this.id = id;
		this.name = name;
		this.state = new ProductState();
		this.description = description;
		this.price = price;
		this.priority = priority;
	}

	public ProductState getState() {
		return state;
	}

	public void setState(ProductState state) {
		this.state = state;
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
	
	public void setNextState() {
		if(this.state != new ProductState(productState.SERVED)) {
			this.state = new ProductState(this.state.getTypeOrdinal()+1);
		}
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", priority=" + priority + "]";
	}
	
	public boolean equals(Product u) {
		return (this.name.compareTo(u.getName())== 0 
				&& this.description.compareTo(u.getDescription())== 0
				&& this.price == u.getPrice()); // TODO shouldn't it be the id only?
	}

	public void cancel() {
		this.state = new ProductState(productState.CANCELLED);
	}

	public boolean isReady() {
		return this.state.isReady();
	}

	public boolean isCancelled() {
		return this.state.isCancelled();
	}
	
}