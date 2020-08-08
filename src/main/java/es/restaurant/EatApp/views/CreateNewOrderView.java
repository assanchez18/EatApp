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

	public String interactGet(Order order) {
		this.model.addAttribute(ORDER_TAG, order);
		setStatusOk();
		return CREATE_NEW_ORDER_VIEW;
	}

	public String interactPost(Order order) {
		this.model.addAttribute(ORDER_TAG, order);
		this.session.setAttribute(ORDER_TAG, order);
		this.session.removeAttribute(ORDER_IN_PROGRESS);
		setStatusOk();
		return SHOW_ORDER_VIEW;
	}

	public Order getOrder() {
		return (Order) this.session.getAttribute(ORDER_TAG);
	}

	public boolean getOrderInProcess() {
		Object value = this.session.getAttribute(ORDER_IN_PROGRESS);
		if(value == null) {
			return false;
		}
		return (boolean) value;
	}

}