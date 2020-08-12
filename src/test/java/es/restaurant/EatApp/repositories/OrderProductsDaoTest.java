package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.OrderToReview;

@RunWith(SpringRunner.class)
public class OrderProductsDaoTest {
	
	@Test
	public void getAllOrdersFromUser() {
		int userIdTest = 3;
		assertNotNull("Error, cannot get the expected order", OrderProductsDao.getOrderProductsDao().getAllOrdersFromUser(userIdTest));
	}

	@Test
	public void getAllProductsFromOrderToReviewByIdTest() {
		int orderIdTest = 1;
		OrderToReview orderToReview = OrderProductsDao.getOrderProductsDao().getOrderToReviewById(orderIdTest);
		assertTrue("Wrong order ID", orderToReview.getId() == orderIdTest);
		assertTrue("The review is not empty", orderToReview.getReview().isEmpty());
		assertTrue("The retrieved number of products is not ok", orderToReview.getProductReviews().size() == 1);
	}
	

}
