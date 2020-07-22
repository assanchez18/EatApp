package es.restaurant.EatApp.models;



public class Product {
	private Long id;
	private String name;
	private ProductPriority priority;
	private String status; // TODO Add status when managing orders

	public Product(Long id, String name, ProductPriority priority) {
		this.id = id;
		this.name = name;
		this.priority = priority;
		//this.status = status;
	}
}