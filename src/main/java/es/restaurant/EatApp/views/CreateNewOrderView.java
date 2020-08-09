package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;

public class CreateNewOrderView extends OrderView {

	private static final String CREATE_NEW_ORDER_VIEW = "createNewOrder";

	public CreateNewOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interact(Order order) {
		this.model.addAttribute(ORDER_TAG, order);
		setStatusOk();
		if(this.getOrder() != null && !this.isOrderInProgress()) {
			return SHOW_ORDER_VIEW;
		}
		return CREATE_NEW_ORDER_VIEW;
	}

	public void updateSession(Order order) {
		this.session.setAttribute(ORDER_TAG, order);
		this.session.removeAttribute(ORDER_IN_PROGRESS);
	}

	public int getTableCode() {
		return (int) this.session.getAttribute(TABLE_TAG);
	}

}