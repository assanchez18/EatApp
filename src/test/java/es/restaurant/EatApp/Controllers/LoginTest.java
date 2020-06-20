package es.restaurant.EatApp.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import es.restaurant.EatApp.GeneralControllers.LoginController;
import es.restaurant.EatApp.Models.User;
import es.restaurant.EatApp.Models.UserBuilder;
import es.restaurant.EatApp.Models.Repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoginTest {

    @Autowired
    private UserRepository userRepo;
    
	private LoginController controller;
	private User user;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() {
		this.user = new UserBuilder().sergio().build();
		userRepo.save(user);
		this.controller = new LoginController(userRepo);
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
    @Test
    public void existingUserLogin() throws Exception {
        this.mockMvc.perform(
                post("/login")
                        .param("email", this.user.getEmail())
                        .param("password", this.user.getPassword()))
                .andExpect(status().isOk());
    }
	
    @Test
    public void notExistingUserLoginError() throws Exception {
    	User u = new UserBuilder().build();
               
        this.mockMvc.perform(
                post("/login")
                        .param("email", u.getEmail())
                        .param("password", u.getPassword()))
                .andExpect(status().is(HttpServletResponse.SC_UNAUTHORIZED));
    }
}
