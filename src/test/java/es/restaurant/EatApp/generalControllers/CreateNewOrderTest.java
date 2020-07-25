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


import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.OrderBuilder;
import es.restaurant.EatApp.models.ProductBuilder;

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
    public void createOrder() throws Exception {
    	Order order = new OrderBuilder().baseOrder().products(new ProductBuilder().map()).build();
    	int[] ids = new int[order.getProducts().size()];
    	int[] amounts = new int[order.getProducts().size()];
//    	order.getProducts().forEach((product, amount) -> {
//    		ids.
//		});
//    	
//    	this.mockMvc.perform(
//                post("/createOrder")
//                        .queryParam(View.EMAIL_TAG, ids)
//                        .param(LoginView.PASSWORD_TAG, user.getPassword()))
//                .andExpect(status().isOk());
    }

}
