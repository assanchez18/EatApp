package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;

public class ProductView extends View {

	private static final String TAG_ORDERS = "orders";
	public static final String TAG_USER_ID = "userId";
	public static final String TAG_PRODUCT_ID = "productId";
	private static final String MSG_PRODUCT_MODIFICATION_ERROR = "Ha habido un problema modificando el producto";
	private static final String VIEW_MANAGE_PRODUCT_STATUS_ORDERS = "manageProductStatusOrders.html";
	private static final String VIEW_SHOW_ORDER = "showOrder.html";

	public ProductView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public ProductView(HttpServletResponse res) {
		super(res);
	}
	
	public int getUserId() {
		return Integer.decode(this.request.getParameter(TAG_USER_ID));
	}

	public int getProductId() {
		return Integer.decode(this.request.getParameter(TAG_PRODUCT_ID));
	}

	public String error() {	
		return returnErrorWithMessage(MSG_PRODUCT_MODIFICATION_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}
	
	public String interact(Collection<Order> orders) {
		setResponse(orders);
		return VIEW_MANAGE_PRODUCT_STATUS_ORDERS;
	}

	public String interact(Collection<Order> orders, boolean isCommensal) {
		setResponse(orders);
		if(isCommensal) {
			return redirectCommensal();
		}
		return redirectEmployee();
	}

	private void setResponse(Collection<Order> orders) {
		this.model.addAttribute(TAG_ORDERS, orders);
		setStatusOk();
	}

	private String redirectEmployee() {
		return VIEW_MANAGE_PRODUCT_STATUS_ORDERS;
	}

	private String redirectCommensal() {
		return VIEW_SHOW_ORDER;
	}

}
