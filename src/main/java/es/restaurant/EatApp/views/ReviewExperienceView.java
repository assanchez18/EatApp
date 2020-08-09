package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;

public class ReviewExperienceView extends OrderView {

	private static final String ORDERS_TO_REVIEW_TAG = "ordersToReview";
	private static final String ORDERS_REVIEW_VIEW = "orderReview";
	private static final String REVIEW_TAG = "review";
	private static final String ERROR_NO_REVIEW_MSG = "No has introducido ninguna review";
	private static final String ORDER_ID_TAG = "orderId";
	public ReviewExperienceView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public String interact(List<Order> orders) {
		this.model.addAttribute(ORDERS_TO_REVIEW_TAG, orders);
		setStatusOk();
		return ORDERS_REVIEW_VIEW;
	}

	public String interact() {
		setStatusOk();
		return MAIN_USER_VIEW;
	}
	
	public String getReview() {
		String opinion = this.request.getParameter(REVIEW_TAG);
		return opinion.isEmpty() ? null : opinion;
	}

	public String noReviewError() {
		return returnErrorWithMessage(ERROR_NO_REVIEW_MSG, HttpServletResponse.SC_BAD_REQUEST, ERROR_VIEW);
	}

	public int getOrderId() {
		return Integer.parseInt(this.request.getParameter(ORDER_ID_TAG));
	}

	
}
