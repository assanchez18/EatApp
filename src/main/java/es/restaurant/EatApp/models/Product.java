package es.restaurant.EatApp.models;

import java.util.ArrayList;
import java.util.List;

import es.restaurant.EatApp.models.ProductState.productState;

public class Product {

	private int id;
	private String name;
	private String description;
	private double price;
	private ProductState state;
	private ProductPriority priority;
	private List<Ingredient> ingredients;
	
	public Product(int id, String name, String description, double price, int priority) {
		this.id = id;
		this.name = name;
		this.state = new ProductState();
		this.description = description;
		this.price = price;
		this.priority = new ProductPriority(priority);
		this.ingredients = new ArrayList<Ingredient>();
	}
	
	public Product(String name, String description, double price, int priority) {
		this.id = -1;
		this.name = name;
		this.state = new ProductState();
		this.description = description;
		this.price = price;
		this.priority = new ProductPriority(priority);
		this.ingredients = new ArrayList<Ingredient>();
	}
	
	public Product(int id, String name, String description, double price, ProductPriority priority) {
		this.id = id;
		this.name = name;
		this.state = new ProductState();
		this.description = description;
		this.price = price;
		this.priority = priority;
		this.ingredients = new ArrayList<Ingredient>();
	}

	public Product() {
		this.id = -1;
		this.name = "";
		this.state = null;
		this.description = "";
		this.price = 0;
		this.priority = new ProductPriority();
		this.ingredients = new ArrayList<Ingredient>();
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

	public void setBackToKitchen() {
		this.state = new ProductState(productState.QUEUED);
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
	
	public boolean isQueued() {
		return this.state.isQueued();
	}

	public boolean isNew() {
		return this.id == -1;
	}

	public void setIngredients(List<Ingredient> productIngredients) {
		this.ingredients = productIngredients;		
	}

	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public void setStateOpen() {
		this.state.setAsOpen();
	}

	public void setStateQueued() {
		this.state.setAsQueued();
	}
	
}