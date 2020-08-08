package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class ShowIngredientsAmountControllerTest {

	private ShowIngredientsAmountController controller;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.controller = new ShowIngredientsAmountController();
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
    @Test
    public void manageGroceriesHasIngredients() throws Exception {
        this.mockMvc.perform(
                get("/showIngredientsAmount"))
                .andExpect(status().isOk());
    }

}
