package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import es.restaurant.EatApp.models.Order;

public class OrderDao extends Dao {

	private Map<Integer, Order> orders;
	private static OrderDao dao;
	protected static final String TABLE_ORDERS = "orders";
	
	public static OrderDao getOrderDao() {
		if(dao == null) {
			dao = new OrderDao();
		}
		return dao;
	}

	private OrderDao() {
		super();
		this.orders = new HashMap<Integer, Order>();
	}

	public Collection<Order> getOrdersFromCache() {
		return this.orders.values();
	}

	public void saveInCache(Order order) { // TODO TEST
		this.orders.put(order.getUserId(), order);
	}

	public Order takeFromCacheWithUserId(int userId) {
		return this.orders.getOrDefault(userId, new Order());
	}

	public void deleteFromCache(int userId) {
		this.orders.remove(userId);
	}

	public boolean updateReview(String review, int orderId) {
        String sql = update(TABLE_ORDERS, "review=?" + where("id=?"));
        return (this.db.getJdbcTemplate().update(sql, review, orderId) == 1);
	}

	private RowMapper<String> buildReview() {
		return new RowMapper<String>() {
			public String mapRow(ResultSet result, int rowNum) throws SQLException {
				String review = result.getString("review");
				return review;
			}
		};
	}
	
	public String getReviewFromOrder(int orderId) {
		String sql = select("review") + from(TABLE_ORDERS) + where("id=" + orderId);
		List<String> review = this.db.getJdbcTemplate().query(sql, buildReview());
		return review.isEmpty() ? null : review.get(0);
	}

}
