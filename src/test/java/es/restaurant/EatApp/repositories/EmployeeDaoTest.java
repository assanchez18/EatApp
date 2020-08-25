package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;

@RunWith(SpringRunner.class)
public class EmployeeDaoTest {
	
	@Test
    public void addNNewEmployeeThenRemove() {
        EmployeeDao dao = EmployeeDao.getEmployeeDao();
        User employee = new UserBuilder().employee().build();
        assertTrue("Error adding new employee.", dao.insertNewUser(employee));        
        assertTrue("Error removing employee.", dao.removeEmployee(employee));
    }
}