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

import es.restaurant.EatApp.views.RegisterInTableView;

@RunWith(SpringRunner.class)
public class RegisterInTableTest {
	    
	private RegisterInTableController controller;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.controller = new RegisterInTableController();
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
    @Test
    public void correctRegistrationInTable() throws Exception {
    	int code = 123;
        this.mockMvc.perform(
                post("/registerInTable")
                        .param(RegisterInTableView.CODE_TAG, Integer.toString(code)))
                .andExpect(status().isOk());
    }
	
    @Test
    public void getRegistrationInTable() throws Exception {
    	int code = 123;
        this.mockMvc.perform(
                get("/registerInTable"))
                .andExpect(status().isOk());
    }

    @Test
    public void incorrectRegistrationInTableNotFound() throws Exception {
		int code = 5000;
        this.mockMvc.perform(
                post("/registerInTable")
                        .param(RegisterInTableView.CODE_TAG, Integer.toString(code)))
                .andExpect(status().isNotFound());
    }

}
