package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.UserType;
import es.restaurant.EatApp.models.Employee;

public class WaiterDao extends EmployeeDao {
	
	private List<Employee> waiters;
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
		this.waiters = new ArrayList<Employee>();
		loadWaiters();
	}

	private void loadWaiters() {
		this.waiters = executeWaiterQuery(selectAllWaiters());
	}

	private RowMapper<Employee> buildWaiter() {
		return new RowMapper<Employee>() {
			public Employee mapRow(ResultSet result, int rowNum) throws SQLException {
				Employee waiter = new Employee(result.getInt("id"),result.getString("email"),result.getString("password"), result.getInt("type"));
				return waiter;
        	}
		};
	}

	public String selectAllWaiters() {
		return selectAllFromUser() + where("type = " + USER_TYPE);
	}

	public List<Employee> executeWaiterQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildWaiter());
	}

	public List<Employee> getWaiters() {
		return this.waiters;
	}

	public Employee getWaiter(String email) {
		for(Employee w : this.waiters) {
			if (w.getEmail().compareTo(email)==0) {
				return w;
			}
		}
		return new Employee();
	}
}
