package es.restaurant.EatApp.models;

import java.util.Map;

public class Order {
	
	private int id;
	private OrderState state;
	private Map<Product, Integer> products;
	private String parameters;
	private int userId;
	
	public Order() {
		this.id = 1;
		this.state = new OrderState();
	}

	public Order(Map<Product, Integer> products, String parameters, OrderState state) {
		this.products = products;
		this.parameters = parameters;
		this.state = state;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}
	
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	public String getParameters() {
		return this.parameters;
	}

}
