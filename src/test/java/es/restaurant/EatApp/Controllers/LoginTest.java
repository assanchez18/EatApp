package es.restaurant.EatApp.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import es.restaurant.EatApp.GeneralControllers.LoginController;
import es.restaurant.EatApp.Models.User;
import es.restaurant.EatApp.Models.UserBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoginTest {

    
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
		User user = new UserBuilder().sergio().buildSQL();
        this.mockMvc.perform(
                post("/login")
                        .param("email", user.getEmail())
                        .param("password", user.getPassword()))
                .andExpect(status().isOk());
    }
	
    @Test
    public void notExistingUserLoginError() throws Exception {
    	User u = new UserBuilder().buildSQL();
               
        this.mockMvc.perform(
                post("/login")
                        .param("email", u.getEmail())
                        .param("password", u.getPassword()))
                .andExpect(status().is(HttpServletResponse.SC_UNAUTHORIZED));
    }
}
