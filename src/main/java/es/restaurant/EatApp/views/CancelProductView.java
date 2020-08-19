package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;

public class CancelProductView extends ProductView {

	private static final String VIEW_SHOW_ORDER = "showOrder";

	public CancelProductView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public CancelProductView(HttpServletResponse res) {
		super(res);
	}

	public String showOrder(Collection<Order> orders) {
		setStatusOk();
		this.prepareModel(orders);
		return VIEW_SHOW_ORDER;
	}
}
