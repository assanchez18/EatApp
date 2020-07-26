package es.restaurant.EatApp.generalControllers;

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
public class CreateNewOrderTest {

	private CreateNewOrderController controller;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.controller = new CreateNewOrderController();
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
    @Test
    public void createOrderOkWithParameters() throws Exception {
    	String[] ids = {"1","2"};
    	String[] amounts = {"1","2"};
    	String parameters = "Text";
    	
    	this.mockMvc.perform(post("/createOrder")
    			.queryParam("ids[]", ids)
    			.queryParam("amounts[]", amounts)
    			.queryParam("parameters", parameters))
    			.andExpect(status().isOk());
    }
    
    @Test
    public void createOrderOkWithoutParameters() throws Exception {
    	String[] ids = {"1","2"};
    	String[] amounts = {"1","2"};
    	
    	this.mockMvc.perform(post("/createOrder")
    			.queryParam("ids[]", ids)
    			.queryParam("amounts[]", amounts))
    			.andExpect(status().isOk());
    }
    
    @Test
    public void createOrderErrorIsEmpty() throws Exception {
    	String[] ids = {"1","2"};
    	String[] amounts = {"0","0"};
    	String parameters = "Text";
    	
    	this.mockMvc.perform(post("/createOrder")
    			.queryParam("ids[]", ids)
    			.queryParam("amounts[]", amounts)
    			.queryParam("parameters", parameters))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void createOrderErrorIsNotValidProductIdInDatabase() throws Exception {
    	String[] ids = {"1","2"};// TODO Secure database -> Failing
    	String[] amounts = {"1","2"};
    	String parameters = "Text";
    	
    	this.mockMvc.perform(post("/createOrder")
    			.queryParam("ids[]", ids)
    			.queryParam("amounts[]", amounts)
    			.queryParam("parameters", parameters))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void createOrderErrorIsNotValidProductIdIsNotANumber() throws Exception {
    	String[] ids = {"badId"};
    	String[] amounts = {"1"};
    	String parameters = "Text";
    	
    	this.mockMvc.perform(post("/createOrder")
    			.queryParam("ids[]", ids)
    			.queryParam("amounts[]", amounts)
    			.queryParam("parameters", parameters))
    			.andExpect(status().isBadRequest());
    }
    
    @Test
    public void createOrderErrorIsNotValidAmountIsNotANumber() throws Exception {
    	String[] ids = {"1"};
    	String[] amounts = {"badAmount"};
    	String parameters = "Text";
    	
    	this.mockMvc.perform(post("/createOrder")
    			.queryParam("ids[]", ids)
    			.queryParam("amounts[]", amounts)
    			.queryParam("parameters", parameters))
    			.andExpect(status().isBadRequest());
    }

}
