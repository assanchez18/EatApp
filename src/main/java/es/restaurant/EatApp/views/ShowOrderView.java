package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;

public class ShowOrderView extends View {
	public ShowOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interact(Order order) {
		this.session.setAttribute("order", order);
		setStatusOk();
		return "showOrder";
	}
	
	public String interact() {
		setStatusOk(); // Order is in session or there is not order in progress
		return "showOrder";
	}
}