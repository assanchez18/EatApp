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
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.views.CleanNotificationView;
import es.restaurant.EatApp.views.OrderView;
import es.restaurant.EatApp.views.helpers.EmailHelperView;
import es.restaurant.EatApp.views.helpers.OrderHelperView;
import es.restaurant.EatApp.views.helpers.ParameterListReader;
import es.restaurant.EatApp.views.helpers.TableHelperView;

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
				.sessionAttr(EmailHelperView.TAG_EMAIL, w.getEmail())
				.param(CleanNotificationView.TAG_NOTIFICATION_ID, Integer.toString(this.tableCode)))
		.andExpect(status().isOk())
		.andExpect(model().attribute("notifications",
				org.hamcrest.collection.IsIterableWithSize.iterableWithSize(0)));
	}

	@Test
	public void createOrderOkGetRequestWhenNoTableCode() throws Exception {
		this.mockMvc.perform(
				get("/createOrder"))
		.andExpect(status().isPreconditionFailed());
	}

	@Test
	public void createOrderOkGetRequestWhenTableCodeIsNotNullAndThereIsNotOrderInSession() throws Exception {
		int table = 123;
		this.mockMvc.perform(
				get("/createOrder")
				.sessionAttr(TableHelperView.TAG_TABLE, table))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderOkGetRequestWhenTableCodeIsNotNullAndThereIsOrderInSessionNotOpen() throws Exception {
		int table = 123;
		Order order = new OrderBuilder().baseOrder().build();
		this.mockMvc.perform(
				get("/createOrder")
				.sessionAttr(TableHelperView.TAG_TABLE, table)
				.sessionAttr(OrderHelperView.TAG_ORDER, order))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderOkGetRequestWhenTableCodeIsNotNullAndThereIsOrderInSessionOpen() throws Exception {
		int table = 123; Order order = new OrderBuilder().baseOrder().state(new OrderState(OrderState.orderState.OPEN)).build();
		this.mockMvc.perform(get("/createOrder")
				.sessionAttr(TableHelperView.TAG_TABLE, table)
				.sessionAttr(OrderHelperView.TAG_ORDER, order))
		.andExpect(status().isOk());
	}


	@Test
	public void createOrderOkWithParameters() throws Exception {
		String[] ids = {"1","2"};
		String[] amounts = {"1","2"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.sessionAttr(EmailHelperView.TAG_EMAIL, new UserBuilder().commensal().build().getEmail())
				.queryParam(ParameterListReader.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters)
				.sessionAttr(TableHelperView.TAG_TABLE, tableCode))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderOkWithoutParameters() throws Exception {
		String[] ids = {"1","2"};
		String[] amounts = {"1","2"};

		this.mockMvc.perform(post("/createOrder")
				.sessionAttr(EmailHelperView.TAG_EMAIL, new UserBuilder().commensal().build().getEmail())
				.queryParam(ParameterListReader.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.sessionAttr(TableHelperView.TAG_TABLE, tableCode))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderErrorIsEmpty() throws Exception {
		String[] ids = {"1","2"};
		String[] amounts = {"0","0"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.sessionAttr(EmailHelperView.TAG_EMAIL, new UserBuilder().commensal().build().getEmail())
				.queryParam(ParameterListReader.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters)
				.sessionAttr(TableHelperView.TAG_TABLE, tableCode))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void createOrderErrorIsValidButOneProductIdInDatabase() throws Exception {
		String[] ids = {"1","24"};
		String[] amounts = {"1","2"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.sessionAttr(EmailHelperView.TAG_EMAIL, new UserBuilder().commensal().build().getEmail())
				.queryParam(ParameterListReader.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters)
				.sessionAttr(TableHelperView.TAG_TABLE, tableCode))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderErrorIsInvalidProductIdInDatabase() throws Exception {
		String[] ids = {"24"};
		String[] amounts = {"1"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.sessionAttr(EmailHelperView.TAG_EMAIL, new UserBuilder().commensal().build().getEmail())
				.queryParam(ParameterListReader.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters)
				.sessionAttr(TableHelperView.TAG_TABLE, tableCode))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void createOrderErrorIsNotValidProductIdIsNotANumber() throws Exception {
		String[] ids = {"badId"};
		String[] amounts = {"1"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.sessionAttr(EmailHelperView.TAG_EMAIL, new UserBuilder().commensal().build().getEmail())
				.queryParam(ParameterListReader.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters)
				.sessionAttr(TableHelperView.TAG_TABLE, tableCode))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void createOrderErrorIsNotValidAmountIsNotANumber() throws Exception {
		String[] ids = {"1"};
		String[] amounts = {"badAmount"};
		String parameters = "Text";

		this.mockMvc.perform(post("/createOrder")
				.sessionAttr(EmailHelperView.TAG_EMAIL, new UserBuilder().commensal().build().getEmail())
				.queryParam(ParameterListReader.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters)
				.sessionAttr(TableHelperView.TAG_TABLE, tableCode))
		.andExpect(status().isBadRequest());
	}
}
