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

import es.restaurant.EatApp.views.ConfigureQuantityOfIngredientsView;

@RunWith(SpringRunner.class)
public class ConfigureQuantityOfIngredientsTest {

	private ConfigureQuantityOfIngredientsController configureQuantityOfIngredientsController;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.configureQuantityOfIngredientsController = new ConfigureQuantityOfIngredientsController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.configureQuantityOfIngredientsController).build();
		
	}

	@Test
	public void changeQuantityOfIngredientsOk() throws Exception {
		this.mockMvc.perform(
				get("/configureQuantityOfIngredients"));
		this.mockMvc.perform(
				post("/manageQuantityOfIngredient")
						.param(ConfigureQuantityOfIngredientsView.INGREDIENT_ID, "1")
						.param(ConfigureQuantityOfIngredientsView.NEW_AMOUNT, "10"))
		.andExpect(status().isOk());
		//restore original value
		this.mockMvc.perform(
				post("/manageQuantityOfIngredient")
						.param(ConfigureQuantityOfIngredientsView.INGREDIENT_ID, "1")
						.param(ConfigureQuantityOfIngredientsView.NEW_AMOUNT, "1"))
		.andExpect(status().isOk());
	}
}
