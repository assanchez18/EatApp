package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.OrderReview;;

public class OrderReviewDao extends Dao {
	
	private final static String TABLE_NAME = "order_review";
	private static OrderReviewDao dao;

	public static OrderReviewDao getOrderReviewDao() {
		if(dao == null) {
			dao = new OrderReviewDao();
		}
		return dao;
	}
	
	private OrderReviewDao() {	
	}
	
	private RowMapper<OrderReview> buildOrderReview() {
		return new RowMapper<OrderReview>() {
			public OrderReview mapRow(ResultSet result, int rowNum) throws SQLException {
				OrderReview orderReview = new OrderReview(result.getInt("orderId"),
														  result.getInt("userId"),
														  result.getString("review"),
														  result.getBoolean("hasReview"));
				return orderReview;
			}
		};
	}

	public List<OrderReview> getAllOrdersWithoutReviewFromUser(int userId) {
		String sql = selectAllFrom(TABLE_NAME) + where("userId = " + userId) + and(" hasReview = 0");
		List<OrderReview> orders = executeOrderReviewQuery(sql);
		return orders.isEmpty() ? null : orders;
	}

	private List<OrderReview> executeOrderReviewQuery(String sql) {	
		return this.db.getJdbcTemplate().query(sql, buildOrderReview());
	}

	public boolean updateReview(String review, int orderId) {
        String sql = update(TABLE_NAME, "review=?, hasReview=1" + where("orderId=?"));
        return (this.db.getJdbcTemplate().update(sql, review, orderId) == 1);
	}
	
	public boolean updateReviewRestore(String review, int orderId) {
        String sql = update(TABLE_NAME, "review=?, hasReview=0" + where("orderId=?"));
        return (this.db.getJdbcTemplate().update(sql, review, orderId) == 1);
	}
}
