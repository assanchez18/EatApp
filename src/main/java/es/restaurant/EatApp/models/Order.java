package es.restaurant.EatApp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Order extends Observable {
	
	private int id;
	private OrderState state;
	private Map<Product, Integer> products;
	private String parameters;
	private int userId;
	private String review;
	private List<Observer> observers;
	
	public Order() {
		this.id = 1;
		this.state = new OrderState();
		this.observers = new ArrayList<Observer>();
		this.review = "";
	}

	public Order(Map<Product, Integer> products, String parameters, OrderState state) {
		this.products = products;
		this.parameters = parameters;
		this.state = state;
		this.review = "";
	}
	
	public Order(int id, int userId, String review) {
		this.id = id;
		this.userId = userId;
		this.review = review;
		this.products = null;
		this.parameters = "";
		this.state = null;
	}
	
	public void addObservers(Employee waiter) {
		this.observers.add(waiter);
	}
	
	public void changeStatus(Notification.Type type, int tableCode) {
		this.setChanged();
		this.notifyObservers(new Notification(type, tableCode));
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
