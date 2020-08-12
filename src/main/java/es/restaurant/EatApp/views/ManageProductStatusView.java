package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;


public class ManageProductStatusView extends OrderView {
	
	private static final String ORDERS_TAG = "orders";
	private static final String USER_ID_TAG = "userId";
	private static final String OPERATION_TAG = "operation";
	private static final String PRODUCT_ID_TAG = "productId";
	private static final String MANAGE_PRODUCT_STATUS_ORDERS_VIEW = "manageProductStatusOrders.html";

	public ManageProductStatusView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public String interact(Collection<Order> orders) {
		this.model.addAttribute(ORDERS_TAG, orders);
		setStatusOk();
		return MANAGE_PRODUCT_STATUS_ORDERS_VIEW;
	}

	public int getUserId() {
		return Integer.decode(this.request.getParameter(USER_ID_TAG));
	}

	public int getProductId() {
		return Integer.decode(this.request.getParameter(PRODUCT_ID_TAG));
	}

	public String getOperation() {
		return this.request.getParameter(OPERATION_TAG);
	}
}