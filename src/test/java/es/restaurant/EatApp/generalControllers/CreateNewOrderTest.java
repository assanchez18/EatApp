package es.restaurant.EatApp.generalControllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map.Entry;

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
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.ProductDao;
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
				.sessionAttr(OrderView.TABLE_TAG, table))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderOkGetRequestWhenTableCodeIsNotNullAndThereIsOrderInSessionNotOpen() throws Exception {
		int table = 123;
		Order order = new OrderBuilder().baseOrder().build();
		this.mockMvc.perform(
				get("/createOrder")
				.sessionAttr(OrderView.TABLE_TAG, table)
				.sessionAttr(OrderView.ORDER_TAG, order))
		.andExpect(status().isOk());
	}

	@Test
	public void createOrderOkGetRequestWhenTableCodeIsNotNullAndThereIsOrderInSessionOpen() throws Exception {
		int table = 123; Order order = new OrderBuilder().baseOrder().state(new OrderState(OrderState.orderState.OPEN)).build();
		this.mockMvc.perform(get("/createOrder")
				.sessionAttr(OrderView.TABLE_TAG, table)
				.sessionAttr(OrderView.ORDER_TAG, order))
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

	@Test
	public void createEmptyOrder() throws Exception {
		Order order = this.createNewOrderController.createEmptyOrder();
		assertFalse("Error, empty order does not have base products!", order.getProducts().isEmpty());
		assertEquals("Error, empty order does not have all base products!", order.getProducts().size(), ProductDao.getProductDao().getProducts().size());
		assertTrue("Error, empty order has parameters!", order.getParameters().isEmpty());
		assertEquals("Error, empty order does not have userId 0", order.getUserId(),0);
		assertEquals("Error, empty order does not have Id 0", order.getId(), 0);
		assertEquals("Error, empty order does not have state BASE", order.getState().getTypeOrdinal(), OrderState.orderState.BASE.ordinal());
	}

	@Test
	public void mergeOrders() throws Exception {
		Order baseOrder = this.createNewOrderController.createEmptyOrder();
		Order otherOrder = new OrderBuilder().baseOrder().state(new OrderState(OrderState.orderState.COOKING)).parameters("test").userId(2).build();
		Order mergedOrder = this.createNewOrderController.mergeOrders(baseOrder, otherOrder);
		System.out.println("Donde");

		assertEquals("Error, Merge Orders fails in id", mergedOrder.getId(), otherOrder.getId());
		assertEquals("Error, Merge Orders fails in parameters", mergedOrder.getParameters(), otherOrder.getParameters());
		assertEquals("Error, Merge Orders fails in state", mergedOrder.getState(), otherOrder.getState());
		assertEquals("Error, Merge Orders fails in userId", mergedOrder.getUserId(), otherOrder.getUserId());
		assertEquals("Error, Merged Order does not have all base products!", mergedOrder.getProducts().size(), ProductDao.getProductDao().getProducts().size());
		for(Entry<Product, Integer> product : otherOrder.getProducts().entrySet()) {
			for(Entry<Product, Integer>checkProduct : mergedOrder.getProducts().entrySet()) {
				if(checkProduct.getKey().equals(product.getKey())) {
					checkProduct.setValue(product.getValue());
					assertTrue("Error, mergedOrder does not have the value it should have in product " + checkProduct.getKey().getName(), checkProduct.getValue() == product.getValue());
				}
			}
		}
	}
}
