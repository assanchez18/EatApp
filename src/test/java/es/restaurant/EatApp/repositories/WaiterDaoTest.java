package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.Employee;
import es.restaurant.EatApp.repositories.WaiterDao;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;

@RunWith(SpringRunner.class)
public class WaiterDaoTest {
	
	@Test
    public void selectWaiterTest() {
        WaiterDao dao = WaiterDao.getWaiterDao();
        List<Employee> waiters = dao.executeWaiterQuery(dao.selectAllWaiters() + dao.and(("id = 4")));
        User waiter = new UserBuilder().waiter().build();
        
        assertTrue("Error, missing base waiter", waiters.size() == 1);
        assertTrue("Error, waiter user is not correct", waiter.equals(waiters.get(0)));
    }
}
