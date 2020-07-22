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
				get("/quantityOfIngredients"));
		this.mockMvc.perform(
				post("/manageQuantityOfIngredient")
						.requestAttr("ingredient_id", 1)
						.requestAttr("new_quantity", 10))
		.andExpect(status().isOk());
	}
}
