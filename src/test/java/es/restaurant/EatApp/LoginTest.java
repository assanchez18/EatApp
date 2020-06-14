package es.restaurant.EatApp;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import es.restaurant.EatApp.GeneralControllers.LoginController;
import es.restaurant.EatApp.Models.User;
import es.restaurant.EatApp.Models.Repositories.UserRepository;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class LoginTest {

	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepositoryMock;
	
	@Test
	public void existingUserLoginThenSuccessfull() throws Exception {
		
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"))
			.andExpect(forwardedUrl("/login/"));
		
	}
	
	
	
	
	
	
	
	private LoginController controller;
	private Model model;
	
	/*@Before
	public void setup() {
		this.controller = new LoginController();
		this.model.addAttribute("userName","Sergio");
		this.model.addAttribute("password","mag1cPassW0rd!*");
	}*/

	@Test
	public void existingUserLoginThenSuccessfull11() {
		this.controller = new LoginController();
		this.model.addAttribute("userName","Sergio");
		this.model.addAttribute("password","mag1cPassW0rd!*");
		
		String result = this.controller.control(this.model);
		assertEquals("Login unsuccesfull, Error raised!","login", result);
	}
	
	@Test
	public void notExistingUserLoginThenError() {
		this.controller = new LoginController();
		this.model.addAttribute("userName","Sergio");
		this.model.addAttribute("password","mag1cPassW0rd!*");
		
		String result = this.controller.control(this.model);
		assertEquals("Login error! Unkown user has login!",result);
	}

}
