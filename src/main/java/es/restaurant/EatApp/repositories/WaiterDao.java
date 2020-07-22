package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.UserType;
import es.restaurant.EatApp.models.Waiter;

public class WaiterDao extends UserDao{
	
	private List<Waiter> waiters;
	private static WaiterDao dao;
	private final static int USER_TYPE = UserType.userType.WAITER.ordinal();
	
	public static WaiterDao getWaiterDao() {
		if(dao == null) {
			dao = new WaiterDao();
		}
		return dao;
	}
	
	private WaiterDao() {
		super();
		this.waiters = new ArrayList<Waiter>();
		loadWaiters();
	}
	
	private void loadWaiters() {
		this.waiters = executeWaiterQuery(selectAllWaiters());
	}
	
	private RowMapper<Waiter> buildWaiter() {
		return new RowMapper<Waiter>() {
			public Waiter mapRow(ResultSet result, int rowNum) throws SQLException {
				Waiter waiter = new Waiter(result.getLong("id"),result.getString("email"),result.getString("password"), result.getInt("type"));
				return waiter;
        	}
		};
	}

	public String selectAllWaiters() {
		return selectAllFromUser() + where("type = " + USER_TYPE);
	}
	
	public List<Waiter> executeWaiterQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildWaiter());
	}
	
	public List<Waiter> getWaiters() {
		return this.waiters;
	}
	
	public Waiter getWaiter(String email) {
		for(Waiter w : this.waiters) {
			if (w.getEmail().compareTo(email)==0) {
				return w;
			}
		}
		return null;
	}
}
