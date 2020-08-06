package es.restaurant.EatApp.repositories;

import java.util.ArrayList;
import java.util.List;

import es.restaurant.EatApp.models.Order;

public class OrderDao extends UserDao{
	
	private List<Order> orders;
	private static OrderDao dao;
	
	public static OrderDao getWaiterDao() {
		if(dao == null) {
			dao = new OrderDao();
		}
		return dao;
	}
	
	private OrderDao() {
		super();
		this.orders = new ArrayList<Order>();
	}
	
	public void saveInCache(Order order) {
		this.orders.add(order);
	}
}
