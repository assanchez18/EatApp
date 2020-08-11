package es.restaurant.EatApp.models;
import java.util.List;

import org.javatuples.Pair;

public class OrderToReview extends Order {

	private List<Pair<String,Integer>> productReviews;
	private String review;

	public OrderToReview(int orderId, List<Pair<String,Integer>> products, String review) {
		this.id = orderId;
		this.productReviews = products;
		this.review = review;
	}

	public OrderToReview(int id, String review) {
		this.id = id;
		this.review = review;
		this.productReviews = null;
	}

	public String getReview() {
		return this.review;
	}

	public List<Pair<String,Integer>> getProductReviews() {
		return this.productReviews;
	}
}
