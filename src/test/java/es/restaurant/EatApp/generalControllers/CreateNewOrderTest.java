package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import es.restaurant.EatApp.models.OrderState;
import es.restaurant.EatApp.models.OrderState.orderState;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.views.CleanNotificationView;
import es.restaurant.EatApp.views.OrderView;
import es.restaurant.EatApp.views.View;

@RunWith(SpringRunner.class)
public class CreateNewOrderTest {

	private CreateNewOrderController createNewOrderController;
	private CleanNotificationController cleanController;
	private MockMvc mockMvc;

	private int tableCode = 123;

	@Before
	public void setup() {
		this.createNewOrderController = new CreateNewOrderController();
		this.cleanController = new CleanNotificationController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.createNewOrderController,
				this.cleanController).build();
	}

	@After
	public void tearDown() throws Exception {
		User w = new UserBuilder().waiter().build();
		this.mockMvc.perform(
				post("/cleanNotification")
				.sessionAttr(View.EMAIL_TAG, w.getEmail())
				.param(CleanNotificationView.NOTIFICATION_ID, Integer.toString(this.tableCode)))
		.andExpect(status().isOk())
		.andExpect(model().attribute("notifications",
				org.hamcrest.collection.IsIterableWithSize.iterableWithSize(0)));
	}

	@Test
	public void createOrderOkGetRequest() throws Exception {
		this.mockMvc.perform(
				get("/createOrder"))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderOkGetRequestWhenAnOrderIsInProgress() throws Exception {
		Order orderInProgress = new OrderBuilder().baseOrder().state(new OrderState(orderState.OPEN)).build();
		this.mockMvc.perform(
				get("/createOrder")
				.sessionAttr(OrderView.ORDER_TAG, orderInProgress))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderOkWithParameters() throws Exception {
		String[] ids = {"1","2"};
		String[] amounts = {"1","2"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.queryParam(OrderView.IDS_TAG, ids)
				.queryParam(OrderView.AMOUNTS_TAG, amounts)
				.queryParam(OrderView.PARAMS_TAG, parameters)
				.sessionAttr(OrderView.TABLE_TAG, tableCode))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderOkWithoutParameters() throws Exception {
		String[] ids = {"1","2"};
		String[] amounts = {"1","2"};

		this.mockMvc.perform(post("/createOrder")
				.queryParam(OrderView.IDS_TAG, ids)
				.queryParam(OrderView.AMOUNTS_TAG, amounts)
				.sessionAttr(OrderView.TABLE_TAG, tableCode))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderErrorIsEmpty() throws Exception {
		String[] ids = {"1","2"};
		String[] amounts = {"0","0"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.queryParam(OrderView.IDS_TAG, ids)
				.queryParam(OrderView.AMOUNTS_TAG, amounts)
				.queryParam(OrderView.PARAMS_TAG, parameters)
				.sessionAttr(OrderView.TABLE_TAG, tableCode))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void createOrderErrorIsValidButOneProductIdInDatabase() throws Exception {
		String[] ids = {"1","24"};
		String[] amounts = {"1","2"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.queryParam(OrderView.IDS_TAG, ids)
				.queryParam(OrderView.AMOUNTS_TAG, amounts)
				.queryParam(OrderView.PARAMS_TAG, parameters)
				.sessionAttr(OrderView.TABLE_TAG, tableCode))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderErrorIsInvalidProductIdInDatabase() throws Exception {
		String[] ids = {"24"};
		String[] amounts = {"1"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.queryParam(OrderView.IDS_TAG, ids)
				.queryParam(OrderView.AMOUNTS_TAG, amounts)
				.queryParam(OrderView.PARAMS_TAG, parameters)
				.sessionAttr(OrderView.TABLE_TAG, tableCode))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void createOrderErrorIsNotValidProductIdIsNotANumber() throws Exception {
		String[] ids = {"badId"};
		String[] amounts = {"1"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.queryParam(OrderView.IDS_TAG, ids)
				.queryParam(OrderView.AMOUNTS_TAG, amounts)
				.queryParam(OrderView.PARAMS_TAG, parameters)
				.sessionAttr(OrderView.TABLE_TAG, tableCode))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void createOrderErrorIsNotValidAmountIsNotANumber() throws Exception {
		String[] ids = {"1"};
		String[] amounts = {"badAmount"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.queryParam(OrderView.IDS_TAG, ids)
				.queryParam(OrderView.AMOUNTS_TAG, amounts)
				.queryParam(OrderView.PARAMS_TAG, parameters)
				.sessionAttr(OrderView.TABLE_TAG, tableCode))
		.andExpect(status().isBadRequest());
	}

}
