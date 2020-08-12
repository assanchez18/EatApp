package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;


public class ShowOrderView extends OrderView {

	public ShowOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public ShowOrderView(HttpServletResponse res) {
		super(res);
	}	

	public String interact() {
		setStatusOk();
		return SHOW_ORDER_VIEW;
	}

	public String interact(Order order) {
		this.model.addAttribute(ORDER_TAG, order);
		this.session.setAttribute(ORDER_TAG, order);
		return interact();
	}

}