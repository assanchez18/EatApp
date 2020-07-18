package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.UserType;
import es.restaurant.EatApp.models.Waiter;

public class WaiterDao {
	
	private Database db;
	private List<Waiter> waiters;
	private static WaiterDao dao;
	
	public static WaiterDao getWaiterDao() {
		if(dao == null) {
			dao = new WaiterDao();
		}
		return dao;
	}
	
	private WaiterDao() {
		this.db = Database.getDatabase();
		this.waiters = new ArrayList<Waiter>();
		loadWaiters();
	}
	
	private void loadWaiters() {
		this.waiters = executeQuery(selectAllWaiters());
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
		return "SELECT * FROM user WHERE type = " + UserType.userType.WAITER.ordinal();
	}
	
	public List<Waiter> executeQuery(String sql) {
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
