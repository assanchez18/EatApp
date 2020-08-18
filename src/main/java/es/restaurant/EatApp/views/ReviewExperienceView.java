package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import es.restaurant.EatApp.models.OrderToReview;

public class ReviewExperienceView extends OrderView {

	private static final String TAG_ORDERS_TO_REVIEW = "ordersToReview";
	private static final String VIEW_SHOW_ALL_USER_ORDERS_TO_REVIEW = "orderReview";
	private static final String VIEW_SHOW_ORDER_TO_REVIEW = "showOrderToReview";
	public static final String TAG_REVIEW = "review";
	public static final String TAG_ORDER_ID = "orderId";
	private static final String MSG_WRONG_ORDER_ID_ERROR = "El id de la orden es incorrecto";
	public ReviewExperienceView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public String showAllOrdersToReview(List<OrderToReview> orders) {
		this.model.addAttribute(TAG_ORDERS_TO_REVIEW, orders);
		setStatusOk();
		return VIEW_SHOW_ALL_USER_ORDERS_TO_REVIEW;
	}

	public String interact() {
		setStatusOk();
		return VIEW_MAIN_USER;
	}
	
	public String getReview() {
		return this.request.getParameter(TAG_REVIEW);
	}

	public int getOrderId() {
		return Integer.parseInt(this.request.getParameter(TAG_ORDER_ID));
	}

	public String errorIncorrectId() {
		return returnErrorWithMessageAndErrorCode(MSG_WRONG_ORDER_ID_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}

	public String showOrderToReview(OrderToReview order) {
		this.model.addAttribute(TAG_ORDER, order);
		setStatusOk();
		return VIEW_SHOW_ORDER_TO_REVIEW;
	}

	
}
