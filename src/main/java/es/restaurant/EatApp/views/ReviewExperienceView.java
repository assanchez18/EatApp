package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import es.restaurant.EatApp.models.OrderToReview;

public class ReviewExperienceView extends OrderView {

	private static final String ORDERS_TO_REVIEW_TAG = "ordersToReview";
	private static final String SHOW_ALL_USER_ORDERS_TO_REVIEW_VIEW = "orderReview";
	private static final String SHOW_ORDER_TO_REVIEW_VIEW = "showOrderToReview";
	public static final String REVIEW_TAG = "review";
	public static final String ORDER_ID_TAG = "orderId";
	private static final String ERROR_WRONG_ORDER_ID = "El id de la orden es incorrecto";
	public ReviewExperienceView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public String showAllOrdersToReview(List<OrderToReview> orders) {
		this.model.addAttribute(ORDERS_TO_REVIEW_TAG, orders);
		setStatusOk();
		return SHOW_ALL_USER_ORDERS_TO_REVIEW_VIEW;
	}

	public String interact() {
		setStatusOk();
		return MAIN_USER_VIEW;
	}
	
	public String getReview() {
		return this.request.getParameter(REVIEW_TAG);
	}

	public int getOrderId() {
		return Integer.parseInt(this.request.getParameter(ORDER_ID_TAG));
	}

	public String errorIncorrectId() {
		return returnErrorWithMessage(ERROR_WRONG_ORDER_ID, HttpServletResponse.SC_BAD_REQUEST, ERROR_VIEW);
	}

	public String showOrderToReview(OrderToReview order) {
		this.model.addAttribute(ORDER_TAG, order);
		setStatusOk();
		return SHOW_ORDER_TO_REVIEW_VIEW;
	}

	
}
