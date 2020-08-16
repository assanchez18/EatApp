package es.restaurant.EatApp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Order extends Observable {

	protected int id;
	private OrderState state;
	private Map<Product, Integer> products;
	private String parameters;
	private int userId;
	private List<Observer> observers;

	public Order() {
		this.state = new OrderState();
		this.products = new HashMap<Product, Integer>();
		this.parameters = "";
		this.observers = new ArrayList<Observer>();
	}

	public Order(Map<Product, Integer> products, String parameters) {
		this.products = products;
		this.parameters = parameters;
		this.state = new OrderState();
	}
	
	public Order(Map<Product, Integer> products) {
		this.products = products;
		this.parameters = "";
		this.state = new OrderState();
	}

	public void addObservers(Employee waiter) {
		this.observers.add(waiter);
	}

	public void changeStatus(Notification notification) {
		this.setChanged();
		this.notifyObservers(new Notification(notification.getType(), notification.getOwner()));
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public void calculateNextState() {
		int state = ProductState.productState.values().length;
		for(Product product : this.products.keySet()) {
			if(product.getState().getTypeOrdinal() < state) {
				state = product.getState().getTypeOrdinal();
			}
		}
		this.state = new OrderState(state);
		// TODO Handle PAYED and REVIEWED and test
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

	public boolean isOpen() {
		return this.state.isOpen();
	}
}
