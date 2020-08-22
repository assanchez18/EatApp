package es.restaurant.EatApp.repositories;

import java.util.List;

import es.restaurant.EatApp.models.Employee;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserType;

public class EmployeeDao extends UserDao {

	protected static EmployeeDao dao;
	
	public static EmployeeDao getEmployeeDao() {
		if(dao == null) {
			dao = new EmployeeDao();
		}
		return dao;
	}

	protected EmployeeDao() {
		
	}

	public Employee getEmployeeFromCache(String email) {
		Employee employee = WaiterDao.getWaiterDao().getWaiter(email);
		return (employee != null) ? employee : CookDao.getCookDao().getCook(email);
	}
	
	public List<Employee> getAllEmployeesFromCache() {
		List<Employee> employees = WaiterDao.getWaiterDao().getWaiters();
		employees.addAll(CookDao.getCookDao().getCooks());
		return employees;
	}
	
	public List<User> getAllEmployeesBut(String email) {
		String sql = selectAllFrom(TABLE_NAME) + where("(user.type = " + UserType.userType.ADMIN.ordinal()
														+ or("user.type = " + UserType.userType.WAITER.ordinal()) 
														+ or("user.type = " + UserType.userType.COOK.ordinal() + ")")
														+ and("user.email != \"" + email + "\""));
		return executeQuery(sql);
	}
}
