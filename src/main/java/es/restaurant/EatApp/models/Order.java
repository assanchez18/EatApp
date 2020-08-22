package es.restaurant.EatApp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import es.restaurant.EatApp.repositories.ProductDao;

public class Order extends Observable {

	protected int id;
	private OrderState state;
	private Map<Product, Integer> products;
	private String parameters;
	private int userId;
	private List<Observer> observers;
	private double totalPrice;
	
	public Order() {
		this.id = -1;
		this.state = new OrderState();
		this.products = emptyProducts();
		this.parameters = "";
		this.observers = new ArrayList<Observer>();
		this.totalPrice = 0;
	}

	private Map<Product, Integer> emptyProducts() {
		Map<Product, Integer> products =  new HashMap<Product, Integer>();
		ProductDao productDao = ProductDao.getProductDao();
		for(Product product : productDao.getProducts()) {
			products.put(product, 0);
		}
		return products;
	}

	public Order(Map<Product, Integer> products, String parameters) {
		this.products = products;
		this.parameters = parameters;
		this.state = new OrderState();
		this.totalPrice = 0;
	}

	public Order(Map<Product, Integer> products) {
		this.products = products;
		this.parameters = "";
		this.state = new OrderState();
		this.totalPrice = 0;
	}

	public Order(Integer[] ids, Integer[] amounts, String parameters) {
		this.products = createProducts(ids, amounts);
		this.parameters = parameters;
		this.state = new OrderState();
		this.totalPrice = 0;
	}

	private Map<Product, Integer> createProducts(Integer[] ids, Integer[] amounts) {
		Map<Product, Integer> products =  new HashMap<>();
		ProductDao productDao = ProductDao.getProductDao();
		for (int i = 0; i < ids.length; i++) {
			Product product = productDao.getProductById(ids[i]);
			if(amounts[i] == 0 || product == null) {
				continue;
			}
			products.put(product, amounts[i]);
		}
		return products;
	}

	public void addObservers(Employee waiter) {
		this.observers.add(waiter);
	}

	private void changeStatus(Notification notification) {
		this.setChanged();
		this.notifyObservers(notification);
	}

	public OrderState getState() {
		return state;
	}

	public void setStateToQueued() {
		this.state.setToQueued();
	}
	
	public void setStateToOpen() {
		this.state.setToOpen();
	}

	public void updateState() {
		int state = OrderState.orderState.values().length;
		int isQueuedOrCancelled = 0;
		for(Product product : this.products.keySet()) {
			if(product.getState().isQueued() || product.getState().isCancelled()) {
				isQueuedOrCancelled++;
				continue;
			}
			if(product.getState().getTypeOrdinal() < state) {
				state = product.getState().getTypeOrdinal();
			}
		}
		if(isQueuedOrCancelled == this.products.size()) {
			setQueuedOrCancelledState();
			return;
		}
		this.state = new OrderState(state);
	}

	private void setQueuedOrCancelledState() {
		for(Product product : this.products.keySet()) {
			if(product.getState().isQueued()) {
				this.state = new OrderState(OrderState.orderState.QUEUED);
				return;
			}
		}
		this.state = new OrderState(OrderState.orderState.CANCELLED);
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

	public void setUserId(User user) {
		setUserId(user.getId());
	}

	protected void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isOpen() {
		return this.state.isOpen();
	}
	
	public void calculateTotalPrice() {
		this.totalPrice = 0;
		for (Product p : this.products.keySet()) {
			this.totalPrice += p.getPrice() * this.products.get(p);
		}
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
	}

	public boolean isValid() {
		return this.id != -1;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public void changeStatusToQueued(int tableCode) {
		this.setStateToQueued();
		Notification notification = new NotificationBuilder(tableCode).orderQueued().build();
		this.changeStatus(notification);
	}

	public void changeStatus(Product product, int code) {
		Notification notification = new NotificationBuilder(product, code).build();
		this.changeStatus(notification);
	}

	public void mergeOrder(Order otherOrder) {
		
		otherOrder.getProducts().forEach((otherOrderKey, otherOrderValue) ->
						this.getProducts().forEach((localKey,localValue) -> {
							if (localKey.equals(otherOrderKey)) 
								localValue = otherOrderValue;
						}));
		this.setUserId(otherOrder.getUserId());
		this.setParameters(otherOrder.getParameters());
		this.setState(otherOrder.getState());
		this.setId(otherOrder.getId());
	}

	public void initializeProductsAsQueued() {
		this.getProducts().forEach((p,v) -> p.setStateQueued());
	}

	public void initializeProductsAsOpen() {
		this.getProducts().forEach((p,v) -> p.setStateOpen());
	}

	public boolean hasProducts() {
		return !this.getProducts().isEmpty();
	}
}
