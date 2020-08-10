package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class OrderDaoTest {
	
	@Test
	public void getAllOrdersFromUser() {
		OrderDao dao = OrderDao.getOrderDao();
		int userIdTest = 3;
		String review = "test review";
		int orderIdTest = 2;
		String reviewRestore = "";
		//insert
		assertNotNull("Error, cannot get the expected order", dao.getAllOrdersFromUser(userIdTest));
		//modify
		assertTrue("Error updating review", dao.updateReview(review, orderIdTest));
		assertTrue("Error updating review back to empty", dao.updateReviewRestore(reviewRestore, orderIdTest));
	}

}
