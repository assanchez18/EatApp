package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import es.restaurant.EatApp.generalControllers.LoginController;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;

@RunWith(SpringRunner.class)
public class RegisterInTableTest {
	
	private static final String TABLE = "table";
	private static final String CODE = "code";
    
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
		//User user = new UserBuilder().sergio().buildSQL();
		//TODO Add table
		String code = "123";
        this.mockMvc.perform(
                post("/registerInTable")
                        .param(CODE, code))
                .andExpect(status().isOk());
    }
    
    @Test
    public void incorrectRegistrationInTableNotFound() throws Exception {
		//User user = new UserBuilder().sergio().buildSQL();
		//TODO Add table
		String code = "50001";
        this.mockMvc.perform(
                post("/registerInTable")
                        .param(CODE, code))
                .andExpect(status().isNotFound());
    }

}
