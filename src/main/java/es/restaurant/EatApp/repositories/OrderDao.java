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
	private static final String TABLE_NAME = "orders";
	
	public static OrderDao getOrderDao() {
		if(dao == null) {
			dao = new OrderDao();
		}
		return dao;
	}

	private OrderDao() {
		super();
		this.orders = new HashMap<Integer, Order>();
		this.orders.put(1, new Order());
	}

	public Collection<Order> getOrdersFromCache() {
		return this.orders.values();
	}

	public void saveInCache(Order order) { // TODO TEST
		this.orders.put(order.getId(), order);
	}

	public Order takeFromCacheWithUserId(int userId) {
		return this.orders.get(userId);
	}

	private RowMapper<Order> buildOrderForReview() {
		return new RowMapper<Order>() {
			public Order mapRow(ResultSet result, int rowNum) throws SQLException {
				Order order = new Order(result.getInt("id"),
										result.getInt("userId"),
										result.getString("review"));
				return order;
			}
		};
	}

	public List<Order> getAllOrdersFromUser(int userId) {
		String sql = selectAllFrom(TABLE_NAME) + where("userId = " + userId);
		List<Order> orders = this.db.getJdbcTemplate().query(sql, buildOrderForReview());
		return orders.isEmpty() ? null : orders;
	}

	public boolean updateReview(String review, int orderId) {
        String sql = update(TABLE_NAME, "review=?" + where("id=?"));
        return (this.db.getJdbcTemplate().update(sql, review, orderId) == 1);
	}
	
	public boolean updateReviewRestore(String review, int orderId) {
        String sql = update(TABLE_NAME, "review=?" + where("id=?"));
        return (this.db.getJdbcTemplate().update(sql, review, orderId) == 1);
	}
	
}
