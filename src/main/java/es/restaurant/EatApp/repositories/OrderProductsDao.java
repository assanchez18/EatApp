package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.javatuples.Pair;
import org.springframework.jdbc.core.RowMapper;
import es.restaurant.EatApp.models.OrderToReview;


public class OrderProductsDao extends Dao {

	private static OrderProductsDao dao;
	private static final String TABLE_ORDER_PRODUCTS = "order_products";
	private static final String TAG_NAME = "name";
	private static final String TAG_AMOUNT = "amount";
	
	public static OrderProductsDao getOrderProductsDao() {
		if(dao == null) {
			dao = new OrderProductsDao();
		}
		return dao;
	}

	private OrderProductsDao() {
		super();
	}

	private RowMapper<Pair<String,Integer>> buildProductToReview() {
		return new RowMapper<Pair<String,Integer>>() {
			public Pair<String,Integer> mapRow(ResultSet result, int rowNum) throws SQLException {
				Pair<String,Integer> product = new Pair<String,Integer>(result.getString(TAG_NAME),result.getInt(TAG_AMOUNT));
				return product;
			}
		};
	}

	private List<Pair<String,Integer>> getAllProductsFromOrder(int orderId) {
		String sql = select("products.name as " + TAG_NAME + ", " + TABLE_ORDER_PRODUCTS + ".amount as " + TAG_AMOUNT)
					+ from(ProductDao.TABLE_PRODUCTS +", " + TABLE_ORDER_PRODUCTS)
					+ where("products.id = " + TABLE_ORDER_PRODUCTS +".productId" + and(TABLE_ORDER_PRODUCTS + ".orderId = " + orderId));
		List<Pair<String,Integer>> products = this.db.getJdbcTemplate().query(sql,buildProductToReview());
		return products.isEmpty() ? null : products;
	}

	public OrderToReview getOrderToReviewById(int orderId) {
		return new OrderToReview(orderId, getAllProductsFromOrder(orderId), OrderDao.getOrderDao().getReviewFromOrder(orderId));
	}

	private RowMapper<OrderToReview> buildOrderToReview() {
		return new RowMapper<OrderToReview>() {
			public OrderToReview mapRow(ResultSet result, int rowNum) throws SQLException {
				OrderToReview order = new OrderToReview(result.getInt("id"),
														result.getString("review"));
				return order;
			}
		};
	}

	public List<OrderToReview> getAllOrdersFromUser(int userId) {
		String sql = selectAllFrom(OrderDao.TABLE_ORDERS) + where("userId = " + userId);
		List<OrderToReview> orders = this.db.getJdbcTemplate().query(sql, buildOrderToReview());
		return orders.isEmpty() ? null : orders;
	}

}
