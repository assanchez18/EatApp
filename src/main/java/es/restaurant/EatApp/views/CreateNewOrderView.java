package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.OrderState;

public class CreateNewOrderView extends OrderView {

	private static final String CREATE_NEW_ORDER_VIEW = "createNewOrder";

	public CreateNewOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interact(Order order) {
		setStatusOk();
		Order sessionOrder = this.getOrder();
		if(sessionOrder != null && !(sessionOrder.getState().getTypeOrdinal() == OrderState.orderState.OPEN.ordinal())) {
			return SHOW_ORDER_VIEW;
		}
		this.model.addAttribute(ORDER_TAG, order);
		return CREATE_NEW_ORDER_VIEW;
	}

	public void updateSession(Order order) {
		this.session.setAttribute(ORDER_TAG, order);
	}

	public Integer getTableCode() {
		return (Integer) this.session.getAttribute(TABLE_TAG);
	}

	public String redirectToRegistryInTable() {
		this.response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		return RegisterInTableView.REGISTRY_IN_TABLE_VIEW;
	}

}