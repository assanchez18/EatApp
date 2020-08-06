package es.restaurant.EatApp.repositories;

import java.util.HashMap;
import java.util.Map;

import es.restaurant.EatApp.models.Order;

public class OrderDao extends UserDao{
	
	private Map<Integer, Order> orders;
	private static OrderDao dao;
	
	public static OrderDao getOrderDao() {
		if(dao == null) {
			dao = new OrderDao();
		}
		return dao;
	}
	
	private OrderDao() {
		super();
		this.orders = new HashMap<Integer, Order>();
	}
	
	public void saveInCache(Order order) {
		this.orders.put(order.getId(), order);
	}
	
	public Order takeFromCache(Order order) {
		return this.orders.get(order.getId());
	}
}
