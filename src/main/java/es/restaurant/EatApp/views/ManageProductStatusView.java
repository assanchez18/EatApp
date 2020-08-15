package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;


public class ManageProductStatusView extends OrderView {
	
	private static final String TAG_ORDERS = "orders";
	public static final String TAG_USER_ID = "userId";
	public static final String TAG_OPERATION = "operation";
	public static final String TAG_PRODUCT_ID = "productId";
	private static final String VIEW_MANAGE_PRODUCT_STATUS_ORDERS = "manageProductStatusOrders.html";
	private static final String MSG_PRODUCT_MODIFICATION_ERROR = "Ha habido un problema modificando el producto";

	public ManageProductStatusView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public String interact(Collection<Order> orders) {
		this.model.addAttribute(TAG_ORDERS, orders);
		setStatusOk();
		return VIEW_MANAGE_PRODUCT_STATUS_ORDERS;
	}

	public int getUserId() {
		return Integer.decode(this.request.getParameter(TAG_USER_ID));
	}

	public int getProductId() {
		return Integer.decode(this.request.getParameter(TAG_PRODUCT_ID));
	}

	public String getOperation() {
		return this.request.getParameter(TAG_OPERATION);
	}

	public String error() {	
		return returnErrorWithMessage(MSG_PRODUCT_MODIFICATION_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}
}