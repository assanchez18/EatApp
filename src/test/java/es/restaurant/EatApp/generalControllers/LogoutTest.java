package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LogoutTest {

	private static final String EMAIL = "email";

	private LogoutController controller;	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.controller = new LogoutController();

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void okLogout() throws Exception {
		User user = new UserBuilder().sergio().buildSQL();

		this.mockMvc.perform(
				get("/logout")
				.sessionAttr(EMAIL, user.getEmail()))
		.andExpect(status().is(HttpServletResponse.SC_OK));
	}

}