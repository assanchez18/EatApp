package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;


public class ShowOrderView extends OrderView {

	public ShowOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public String interactGet(Order order) {
		setStatusOk();
		return SHOW_ORDER_VIEW;
	}

	public String interactPost(Order order) {
		this.session.setAttribute(ORDER_TAG, order);
		this.session.setAttribute(ORDER_IN_PROGRESS, true);
		setStatusOk();
		return SHOW_ORDER_VIEW;
	}
}