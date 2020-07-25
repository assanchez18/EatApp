package es.restaurant.EatApp.models;

import java.util.HashMap;
import java.util.Map;

public class ProductBuilder {

	private Product product;

	public ProductBuilder() {
		this.product = new Product(-1, "Test", "testDesc", 1, 1);
	}

	public ProductBuilder baseProduct() {
		//default product in DB
		this.product = new Product(1, "Prod test1", "Desc test 1", 10, 1);
		return this;
	}
	
	public ProductBuilder id(int n) {
		this.product.setId(n);
		return this;
	}

	public ProductBuilder name(String n) {
		this.product.setName(n);
		return this;
	}

	public ProductBuilder description(String n) {
		this.product.setDescription(n);
		return this;
	}

	public ProductBuilder price(double n) {
		this.product.setPrice(n);
		return this;
	}

	public ProductBuilder priority(ProductPriority priority) {
		this.product.setPriority(priority);
		return this;
	}

	public Map<Product, Integer> map(){
		Map<Product, Integer> map = new HashMap<>();
		map.put(baseProduct().build(), 1);
		map.put(baseProduct().id(2).build(), 2);
		map.put(baseProduct().id(3).build(), 30);
		return map;
	}

	public Product build() {
		return this.product;
	}
}
