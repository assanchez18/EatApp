package es.restaurant.EatApp.models;

import java.util.Map;

public class OrderBuilder {

	private Order order;

	public OrderBuilder() {
		this.order = new Order();
	}

	public OrderBuilder baseOrder() {
		//default order in DB
		this.order = new Order();
		return this;
	}

	public OrderBuilder state(OrderState state) {
		this.order.setState(state);
		return this;
	}
	
	public OrderBuilder products(Map<Product, Integer> products) {
		this.order.setProducts(products);
		return this;
	}

	public Order build() {
		return this.order;
	}
}
