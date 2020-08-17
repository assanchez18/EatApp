package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.UserType;
import es.restaurant.EatApp.models.Employee;

public class CookDao extends UserDao {
	
	private List<Employee> employees;
	private static CookDao dao;
	private final static int USER_TYPE = UserType.userType.COOK.ordinal();
	
	public static CookDao getCookDao() {
		if(dao == null) {
			dao = new CookDao();
		}
		return dao;
	}
	
	private CookDao() {
		super();
		this.employees = new ArrayList<Employee>();
		loadEmployees();
	}
	
	private void loadEmployees() {
		this.employees = executeCookQuery(selectAllCooks());
	}
	
	private RowMapper<Employee> buildCook() {
		return new RowMapper<Employee>() {
			public Employee mapRow(ResultSet result, int rowNum) throws SQLException {
				Employee employee = new Employee(result.getInt("id"),result.getString("email"),result.getString("password"), result.getInt("type"));
				return employee;
        	}
		};
	}

	public String selectAllCooks() {
		return selectAllFromUser() + where("type = " + USER_TYPE);
	}
	
	public List<Employee> executeCookQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildCook());
	}
	
	public List<Employee> getCooks() {
		return this.employees;
	}
	
	public Employee getCook(String email) {
		for(Employee w : this.employees) {
			if (w.getEmail().compareTo(email)==0) {
				return w;
			}
		}
		return null;
	}
}
