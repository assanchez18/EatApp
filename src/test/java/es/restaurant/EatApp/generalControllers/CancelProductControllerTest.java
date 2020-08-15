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
import es.restaurant.EatApp.views.View;

@RunWith(SpringRunner.class)
public class CancelProductControllerTest {

	private CancelProductController cancelProductController;
	private CleanNotificationController cleanController;
	private MockMvc mockMvc;
	private Order order;
	private int tableCode = 123;

	@Before
	public void setup() {
		this.cancelProductController = new CancelProductController();
		this.cleanController = new CleanNotificationController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.cancelProductController, this.cleanController).build();
		this.order = new OrderBuilder().baseOrder().build();
		OrderDao.getOrderDao().saveInCache(this.order);
	}
	
	@After
	public void tearDown() throws Exception {
		OrderDao.getOrderDao().deleteFromCache(this.order.getUserId());
		User w = new UserBuilder().waiter().build();
		this.mockMvc.perform(
				post("/cleanNotification")
				.sessionAttr(View.TAG_EMAIL, w.getEmail())
				.param(CleanNotificationView.TAG_NOTIFICATION_ID, Integer.toString(this.tableCode)))
		.andExpect(status().isOk())
		.andExpect(model().attribute("notifications",
				org.hamcrest.collection.IsIterableWithSize.iterableWithSize(0)));
	}
	
	@Test
	public void cancelProductErrorInUserId() throws Exception {
		int userId = 400;
		int productId = 1;
		for(Product product : this.order.getProducts().keySet()) {
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
		int userId = this.order.getUserId();
		int productId = 400;

		this.mockMvc.perform(
				post("/cancelProduct")
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void cancelProductCorrectlyWhenIsEmployee() throws Exception {
		int userId = this.order.getUserId();
		int productId = 1;
		for(Product product : this.order.getProducts().keySet()) {
			productId = product.getId();
			break;
		}		

		this.mockMvc.perform(
				post("/cancelProduct")
				.sessionAttr(View.TAG_EMAIL, new UserBuilder().waiter().build().getEmail())
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void cancelProductCorrectlyWhenIsCommensal() throws Exception {
		int userId = this.order.getUserId();
		int productId = 1;
		for(Product product : this.order.getProducts().keySet()) {
			productId = product.getId();
			break;
		}		

		this.mockMvc.perform(
				post("/cancelProduct")
				.sessionAttr(View.TAG_EMAIL, new UserBuilder().commensal().build().getEmail())
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isOk());
	}
}