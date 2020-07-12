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


import es.restaurant.EatApp.generalControllers.LoginController;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;

@RunWith(SpringRunner.class)
public class LoginTest {

	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
    
	private LoginController controller;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.controller = new LoginController();
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
    @Test
    public void existingUserLogin() throws Exception {
		User user = new UserBuilder().sergio().build();
        this.mockMvc.perform(
                post("/login")
                        .param(EMAIL, user.getEmail())
                        .param(PASSWORD, user.getPassword()))
                .andExpect(status().isOk());
    }

    @Test
    public void unauthorizedLoginError() throws Exception {
    	User u = new UserBuilder().sergio().build();
    	String wrongPassword = "other";

        this.mockMvc.perform(
                post("/login")
                        .param(EMAIL, u.getEmail())
                        .param(PASSWORD, wrongPassword))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void userNotFoundLoginError() throws Exception {
    	User u = new UserBuilder().build();

        this.mockMvc.perform(
                post("/login")
                        .param(EMAIL, u.getEmail())
                        .param(PASSWORD, u.getPassword()))
                .andExpect(status().isUnauthorized());
    }
}
