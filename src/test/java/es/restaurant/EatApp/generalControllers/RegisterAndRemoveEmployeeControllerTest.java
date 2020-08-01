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
import es.restaurant.EatApp.views.RemoveEmployeeView;

@RunWith(SpringRunner.class)
public class RegisterAndRemoveEmployeeControllerTest {
	
	private RegisterEmployeeController registerController;
	private RemoveEmployeeController removeController;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.registerController = new RegisterEmployeeController();
		this.removeController = new RemoveEmployeeController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.registerController,
													   this.removeController).build();
	}

	@Test
	public void registerAndRemoveEmployeeGet() throws Exception {
		this.mockMvc.perform(
                get("/registerEmployee"))
                .andExpect(status().isOk());
		
		this.mockMvc.perform(
				get("/removeEmployee"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("employees"));
	}
	
	@Test
	public void firstRegisterNewEmployeeThenRemoveThisEmployee() throws Exception {
		User user = new UserBuilder().employee().build();
		this.mockMvc.perform(
                post("/registerEmployee")
                	.param(RegisterEmployeeView.EMAIL_TAG, user.getEmail())
                    .param(RegisterEmployeeView.PASSWORD_TAG, user.getPassword())
                    .param(RegisterEmployeeView.USER_TYPE_TAG, Integer.toString(user.getUserType().getTypeOrdinal()))
                    .param(RegisterEmployeeView.REPEATED_PASSWORD_TAG, user.getPassword()))
                .andExpect(status().isOk());
		
		this.mockMvc.perform(
				post("/removeEmployee")
					.param(RemoveEmployeeView.USER_TO_REMOVE_TAG, user.getEmail()))
				.andExpect(status().isOk());
	}
	
	@Test
	public void wrongMailToRegisterThenError() throws Exception {
		User user = new UserBuilder().admin().build();
		this.mockMvc.perform(
                post("/registerEmployee")
                	.param(RegisterEmployeeView.EMAIL_TAG, user.getEmail()))
                .andExpect(status().isBadRequest());
	}

	@Test
	public void wrongPasswordToRegisterThenError() throws Exception {
		User user = new UserBuilder().employee().build();
		this.mockMvc.perform(
                post("/registerEmployee")
                	.param(RegisterEmployeeView.EMAIL_TAG, user.getEmail())
                	.param(RegisterEmployeeView.PASSWORD_TAG, user.getPassword())
                    .param(RegisterEmployeeView.USER_TYPE_TAG, Integer.toString(user.getUserType().getTypeOrdinal()))
                    .param(RegisterEmployeeView.REPEATED_PASSWORD_TAG, "1"))
                .andExpect(status().isBadRequest());
	}

	@Test
	public void wrongUserToRemoveThenError() throws Exception {
		User user = new UserBuilder().employee().build();
		this.mockMvc.perform(
                post("/removeEmployee")
                	.param(RemoveEmployeeView.USER_TO_REMOVE_TAG, user.getEmail()))
                .andExpect(status().isBadRequest());
	}
}
