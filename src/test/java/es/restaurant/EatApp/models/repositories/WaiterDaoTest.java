package es.restaurant.EatApp.models.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.Waiter;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;

@RunWith(SpringRunner.class)
public class WaiterDaoTest {
	
	@Test
    public void selectWaiterTest() {
        WaiterDao dao = WaiterDao.getWaiterDao();
        List<Waiter> waiters = dao.executeQuery(dao.selectAllWaiters() + " AND id = 4");
        User waiter = new UserBuilder().waiter().build();
        
        assertTrue("Error, missing base waiter", waiters.size() == 1);
        assertTrue("Error, waiter user is not correct", waiter.equals(waiters.get(0)));
    }
}