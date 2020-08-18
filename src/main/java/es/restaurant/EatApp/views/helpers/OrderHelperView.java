package es.restaurant.EatApp.views.helpers;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;

public class OrderHelperView {

	public static final String TAG_ORDER = "order";
	private Model model;
	private HttpSession session;
	
	public OrderHelperView(Model model, HttpSession session) {
		this.model = model;
		this.session = session;
	}

	public OrderHelperView(HttpSession session) {
		this.model = null;
		this.session = session;
	}

	public void updateOrder(Order order) {
		this.session.setAttribute(TAG_ORDER, order);
	}

	public Order getOrder() {
		return (Order) this.session.getAttribute(TAG_ORDER);
	}

	public void setOrder(Order order) {
		this.model.addAttribute(TAG_ORDER, order);
	}
	
}
