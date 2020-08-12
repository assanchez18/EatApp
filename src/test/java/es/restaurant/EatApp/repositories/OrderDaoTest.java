package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class OrderDaoTest {
	@Test
	public void getAllOrdersFromUser() {
		String review = "test review";
		int orderIdTest = 2;
		String reviewRestore = "";
		//modify
		assertTrue("Error updating review", OrderDao.getOrderDao().updateReview(review, orderIdTest));
		//restore review
		assertTrue("Error updating review back to empty", OrderDao.getOrderDao().updateReview(reviewRestore, orderIdTest));
	}
}
