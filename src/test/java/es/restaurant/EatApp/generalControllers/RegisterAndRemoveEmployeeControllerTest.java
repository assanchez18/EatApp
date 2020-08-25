package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.views.RegisterEmployeeView;
import es.restaurant.EatApp.views.helpers.EmailHelperView;

@RunWith(SpringRunner.class)
public class RegisterAndRemoveEmployeeControllerTest {
	
	private RegisterController registerController;
	private RemoveEmployeeController removeController;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.registerController = new RegisterController();
		this.removeController = new RemoveEmployeeController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.registerController,
													   this.removeController).build();
	}

	@Test
	public void registerNewUserAsEmployeeAndRemoveEmployeeGet() throws Exception {
		User user = new UserBuilder().admin().build();
		this.mockMvc.perform(
                get("/register"))
                .andExpect(status().isOk());
		
		this.mockMvc.perform(
                get("/register")
                .sessionAttr(EmailHelperView.TAG_EMAIL, user.getEmail()))
                .andExpect(status().isOk());

		this.mockMvc.perform(
				get("/removeEmployee"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("employees"));
	}
	
	@Test
	public void wrongMailToRegisterThenError() throws Exception {
		User user = new UserBuilder().commensal().build();
		this.mockMvc.perform(
                post("/register")
                	.param(EmailHelperView.TAG_EMAIL, user.getEmail()))
                .andExpect(status().isBadRequest());
	}

	@Test
	public void wrongPasswordToRegisterThenError() throws Exception {
		User user = new UserBuilder().employee().build();
		this.mockMvc.perform(
                post("/register")
                	.param(EmailHelperView.TAG_EMAIL, user.getEmail())
                	.param(RegisterEmployeeView.TAG_PASSWORD, user.getPassword())
                    .param(RegisterEmployeeView.TAG_USER_TYPE, Integer.toString(user.getUserType().getTypeOrdinal()))
                    .param(RegisterEmployeeView.TAG_REPEATED_PASSWORD, "1"))
                .andExpect(status().isBadRequest());
	}
}
