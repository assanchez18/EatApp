package es.restaurant.EatApp.repositories;

import es.restaurant.EatApp.models.Employee;

public class EmployeeDao {

	public EmployeeDao() {
	}
	
	public Employee getEmployee(String email) {
		Employee employee = WaiterDao.getWaiterDao().getWaiter(email);
		return (employee != null) ? employee : CookDao.getCookDao().getCook(email);
	}
}
