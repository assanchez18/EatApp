package es.restaurant.EatApp.models;

public class OrderReview {

	private int orderId;
	private int userId;
	private String review;
	private boolean hasReview;
	
	public OrderReview(int orderId, int userId, String review, boolean hasReview) {
		this.orderId = orderId;
		this.userId = userId;
		this.review = review;
		this.hasReview = hasReview;
	}

	public OrderReview(int orderId, int userId) {
		this.orderId = orderId;
		this.userId = userId;
		this.review = "";
		this.hasReview = false;
	}
	
	public int getOrderId() {
		return this.orderId;
	}
}
