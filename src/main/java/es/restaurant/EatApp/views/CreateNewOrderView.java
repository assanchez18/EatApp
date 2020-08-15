package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;

public class CreateNewOrderView extends OrderView {

	private static final String VIEW_CREATE_NEW_ORDER = "createNewOrder";

	public CreateNewOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interact(Order order) {
		setStatusOk();
		Order sessionOrder = this.getOrder();
		if(sessionOrder != null && !(sessionOrder.isOpen())) {
			return SHOW_ORDER_VIEW;
		}
		this.model.addAttribute(TAG_ORDER, order);
		return VIEW_CREATE_NEW_ORDER;
	}

	public void updateSession(Order order) {
		this.session.setAttribute(TAG_ORDER, order);
	}

	public String redirectToRegistryInTable() {
		this.response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		return RegisterInTableView.VIEW_REGISTRY_IN_TABLE;
	}

}