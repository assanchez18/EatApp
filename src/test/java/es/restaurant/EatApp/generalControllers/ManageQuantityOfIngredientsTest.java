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
public class ManageQuantityOfIngredientsTest {

	private ManageQuantityOfIngredientsController manageQuantityOfIngredientsController;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.manageQuantityOfIngredientsController = new ManageQuantityOfIngredientsController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.manageQuantityOfIngredientsController).build();
		
	}

	@Test
	public void changeQuantityOfIngredientsOk() throws Exception {
		this.mockMvc.perform(
				get("/manageQuantityOfIngredient"));
		this.mockMvc.perform(
				post("/manageQuantityOfIngredient")
						.param(ConfigureQuantityOfIngredientsView.TAG_INGREDIENT_ID, "1")
						.param(ConfigureQuantityOfIngredientsView.TAG_NEW_AMOUNT, "10"))
		.andExpect(status().isOk());
		//restore ingredient
		this.mockMvc.perform(
				post("/manageQuantityOfIngredient")
						.param(ConfigureQuantityOfIngredientsView.TAG_INGREDIENT_ID, "1")
						.param(ConfigureQuantityOfIngredientsView.TAG_NEW_AMOUNT, "1"))
		.andExpect(status().isOk());

	}

	@Test
	public void changeQuantityOfWrongIngredientId() throws Exception {
		this.mockMvc.perform(
				get("/manageQuantityOfIngredient"));
		this.mockMvc.perform(
				post("/manageQuantityOfIngredient")
						.param(ConfigureQuantityOfIngredientsView.TAG_INGREDIENT_ID, "-1")
						.param(ConfigureQuantityOfIngredientsView.TAG_NEW_AMOUNT, "10"))
		.andExpect(status().isBadRequest());
	}
}
