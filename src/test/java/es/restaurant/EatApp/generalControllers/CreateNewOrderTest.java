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
	public void createOrderOkGetRequest() throws Exception {
		this.mockMvc.perform(
				get("/createOrder"))
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
    			.queryParam(OrderView.PARAMS_TAG, parameters))
    			.andExpect(status().isOk());
    }
    
    @Test
    public void createOrderOkWithoutParameters() throws Exception {
    	String[] ids = {"1","2"};
    	String[] amounts = {"1","2"};
    	
    	this.mockMvc.perform(post("/createOrder")
    			.queryParam(OrderView.IDS_TAG, ids)
    			.queryParam(OrderView.AMOUNTS_TAG, amounts))
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
    			.queryParam(OrderView.PARAMS_TAG, parameters))
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
    			.queryParam(OrderView.PARAMS_TAG, parameters))
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
    			.queryParam(OrderView.PARAMS_TAG, parameters))
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
    			.queryParam(OrderView.PARAMS_TAG, parameters))
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
    			.queryParam(OrderView.PARAMS_TAG, parameters))
    			.andExpect(status().isBadRequest());
    }

}
