package es.restaurant.EatApp.models;

import java.util.Map;

public class Order {
	
	private int id;
	private OrderState state;
	private Map<Product, Integer> products;
	
	public Order() {
		this.id = 1;
		this.state = new OrderState();
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

}
