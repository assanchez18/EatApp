package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import es.restaurant.EatApp.views.ProductView;

@RunWith(SpringRunner.class)
public class ManageProductStatusControllerTest {

	private ManageProductStatusController controller;
	private MockMvc mockMvc;
	private Order orderWaiter;

	@Before
	public void setup() {
		this.controller = new ManageProductStatusController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
		User waiter = new UserBuilder().waiter().build();
		this.orderWaiter = new OrderBuilder().baseOrder().userId(waiter.getId()).build();
		OrderDao.getOrderDao().saveInCache(this.orderWaiter);

	}
	
	@After
	public void tearDown() throws Exception {
		OrderDao.getOrderDao().deleteFromCache(this.orderWaiter.getUserId());
	}

	@Test
	public void manageProductStatusOkGetRequest() throws Exception {
		this.mockMvc.perform(
				get("/manageProductStatus"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void manageProductOk() throws Exception {
		this.mockMvc.perform(
				get("/manageProductStatus"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void manageProductErrorInUserId() throws Exception {
		int userId = 400;
		int productId = 1;
		for(Product product : this.orderWaiter.getProducts().keySet()) {
			productId = product.getId();
			break;
		}

		this.mockMvc.perform(
				post("/manageProductStatus")
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void manageProductErrorInProductId() throws Exception {
		int userId = this.orderWaiter.getUserId();
		int productId = 400;

		this.mockMvc.perform(
				post("/manageProductStatus")
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void manageProductCorrectly() throws Exception {
		int userId = this.orderWaiter.getUserId();
		int productId = 1;
		for(Product product : this.orderWaiter.getProducts().keySet()) {
			productId = product.getId();
			break;
		}

		this.mockMvc.perform(
				post("/manageProductStatus")
				.queryParam(ProductView.TAG_USER_ID, String.valueOf(userId))
				.queryParam(ProductView.TAG_PRODUCT_ID, String.valueOf(productId)))
		.andExpect(status().isOk());
	}
}