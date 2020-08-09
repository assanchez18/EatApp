package es.restaurant.EatApp.models;

import java.util.HashMap;
import java.util.Map;

public class OrderBuilder {

	private Order order;

	public OrderBuilder() {
		this.order = new Order();
	}

	public OrderBuilder baseOrder() {
		this.order = new Order();
		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(new ProductBuilder().baseProduct().build(), 2);
		this.products(products);
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

	public OrderBuilder parameters(String parameters) {
		this.order.setParameters(parameters);
		return this;
	}

	public OrderBuilder userId(int id) {
		this.order.setUserId(id);
		return this;
	}

	public Order build() {
		return this.order;
	}
}
