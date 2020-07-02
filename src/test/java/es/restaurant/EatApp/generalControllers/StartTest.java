package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;

public class StartTest {
	private static final String EMAIL = "email";

	private StartController controller;	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.controller = new StartController();

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void okStartAlreadyLogged() throws Exception {
		User user = new UserBuilder().sergio().buildSQL();

		this.mockMvc.perform(
				get("/")
				.sessionAttr(EMAIL, user.getEmail()))
			.andExpect(status().is(HttpServletResponse.SC_OK));
	}
	
	@Test
	public void okStartNewSession() throws Exception {
		this.mockMvc.perform(
				get("/"))
			.andExpect(status().is(HttpServletResponse.SC_OK));
	}
}
