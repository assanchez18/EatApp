package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.OrderBuilder;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.views.CleanNotificationView;
import es.restaurant.EatApp.views.ProductView;
import es.restaurant.EatApp.views.helpers.EmailHelperView;

@RunWith(SpringRunner.class)
public class CancelProductControllerTest {

	private CancelProductController cancelProductController;
	private CleanNotificationController cleanController;
	private MockMvc mockMvc;
	private Order orderWaiter;
	private Order orderCommensal;
	private int tableCode = 123;

	@Before
	public void setup() {
		this.cancelProductController = new CancelProductController();
		this.cleanController = new CleanNotificationController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.cancelProductController, this.cleanController).build();
		User waiter = new UserBuilder().waiter().build();
		this.orderWaiter = new OrderBuilder().baseOrder().userId(waiter.getId()).build();
		OrderDao.getOrderDao().saveInCache(this.orderWaiter);
		User commensal = new UserBuilder().commensal().build();
		this.orderCommensal = new OrderBuilder().baseOrder().userId(commensal.getId()).build();
		OrderDao.getOrderDao().saveInCache(this.orderCommensal);
	}
	
	@After
	public void tearDown() throws Exception {
		OrderDao.getOrderDao().deleteFromCache(this.orderWaiter.getUserId());
		OrderDao.getOrderDao().deleteFromCache(this.orderCommensal.getUserId());
		User w = new UserBuilder().waiter().build();
		this.mockMvc.perform(
				post("/cleanNotification")
				.sessionAttr(EmailHelperView.TAG_EMAIL, w.getEmail())
				.param(CleanNotificationView.TAG_NOTIFICATION_ID, Integer.toString(this.tableCode)))
		.andExpect(status().isOk())
		.andExpect(model().attribute("notifications",
				org.hamcrest.collection.IsIterableWithSize.iterableWithSize(0)));
	}
	
	@Test
	public void cancelProductErrorInUserId() throws Exception {
		int userId = 400;
		int productId = 1;
		for(Product product : this.orderWaiter.getProducts().keySet()) {
			productId = product.getId();
			break;
		}

		this.mockMvc.perform(
				post("/cancelProduct")
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void cancelProductErrorInProductId() throws Exception {
		int userId = this.orderWaiter.getUserId();
		int productId = 400;

		this.mockMvc.perform(
				post("/cancelProduct")
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void cancelProductCorrectlyWhenIsEmployee() throws Exception {
		int userId = this.orderWaiter.getUserId();
		int productId = 1;
		for(Product product : this.orderWaiter.getProducts().keySet()) {
			productId = product.getId();
			break;
		}		

		this.mockMvc.perform(
				post("/cancelProduct")
				.sessionAttr(EmailHelperView.TAG_EMAIL, new UserBuilder().waiter().build().getEmail())
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void cancelProductCorrectlyWhenIsCommensal() throws Exception {
		int userId = this.orderCommensal.getUserId();
		int productId = 1;
		for(Product product : this.orderCommensal.getProducts().keySet()) {
			productId = product.getId();
			break;
		}		

		this.mockMvc.perform(
				post("/cancelProduct")
				.sessionAttr(EmailHelperView.TAG_EMAIL, new UserBuilder().commensal().build().getEmail())
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isOk());
	}
}