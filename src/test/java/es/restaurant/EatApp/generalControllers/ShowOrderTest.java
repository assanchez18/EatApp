package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import es.restaurant.EatApp.views.OrderView;

@RunWith(SpringRunner.class)
public class ShowOrderTest {

	private ShowOrderController controller;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.controller = new ShowOrderController();

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void showOrderOkGetRequest() throws Exception {
		this.mockMvc.perform(
				get("/showNewOrder"))
		.andExpect(status().isOk());
	}

	@Test
	public void showOrderOkWithParameters() throws Exception {
		String[] ids = {"1","2"};
		String[] amounts = {"1","2"};
		String parameters = "Text";

		this.mockMvc.perform(post("/showNewOrder")
				.queryParam(OrderView.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters))
		.andExpect(status().isOk());
	}

	@Test
	public void showOrderOkWithoutParameters() throws Exception {
		String[] ids = {"1","2"};
		String[] amounts = {"1","2"};

		this.mockMvc.perform(post("/showNewOrder")
				.queryParam(OrderView.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts))
		.andExpect(status().isOk());
	}

	@Test
	public void showOrderErrorIsEmpty() throws Exception {
		String[] ids = {"1","2"};
		String[] amounts = {"0","0"};
		String parameters = "Text";

		this.mockMvc.perform(post("/showNewOrder")
				.queryParam(OrderView.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void showOrderErrorIsValidButOneProductIdInDatabase() throws Exception {
		String[] ids = {"1","24"};// TODO Secure database -> Failing
		String[] amounts = {"1","2"};
		String parameters = "Text";

		this.mockMvc.perform(post("/showNewOrder")
				.queryParam(OrderView.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters))
		.andExpect(status().isOk());
	}

	@Test
	public void showOrderErrorIsNotValidProductIdInDatabase() throws Exception {
		String[] ids = {"24"};// TODO Secure database -> Failing
		String[] amounts = {"1"};
		String parameters = "Text";

		this.mockMvc.perform(post("/showNewOrder")
				.queryParam(OrderView.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void showOrderErrorIsNotValidProductIdIsNotANumber() throws Exception {
		String[] ids = {"badId"};
		String[] amounts = {"1"};
		String parameters = "Text";

		this.mockMvc.perform(post("/showNewOrder")
				.queryParam(OrderView.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void showOrderErrorIsNotValidAmountIsNotANumber() throws Exception {
		String[] ids = {"1"};
		String[] amounts = {"badAmount"};
		String parameters = "Text";

		this.mockMvc.perform(post("/showNewOrder")
				.queryParam(OrderView.TAG_IDS, ids)
				.queryParam(OrderView.TAG_AMOUNTS, amounts)
				.queryParam(OrderView.TAG_PARAMS, parameters))
		.andExpect(status().isBadRequest());
	}

}
