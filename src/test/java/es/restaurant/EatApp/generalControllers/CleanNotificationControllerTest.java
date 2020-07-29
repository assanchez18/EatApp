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

import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.views.CleanNotificationView;
import es.restaurant.EatApp.views.View;

@RunWith(SpringRunner.class)
public class CleanNotificationControllerTest {

	private StartController startController;
	private AskForHelpController askController;
	private CleanNotificationController cleanNotificationController;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.startController = new StartController();
		this.askController = new AskForHelpController();
		this.cleanNotificationController = new CleanNotificationController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.startController,
													   this.askController,
													   this.cleanNotificationController).build();
	}

	@Test
	public void cleanNotifications() throws Exception {
		int tableCode = 123;
		User w = new UserBuilder().waiter().build();
		this.mockMvc.perform(
				get("/"));
		this.mockMvc.perform(
                get("/askForHelp")
                        .sessionAttr(View.TABLE_TAG, tableCode))
                .andExpect(status().isOk());
		this.mockMvc.perform(
				post("/cleanNotification")
					.sessionAttr(View.EMAIL_TAG, w.getEmail())
					.param(CleanNotificationView.NOTIFICATION_ID, Integer.toString(tableCode)))
		.andExpect(status().isOk())
		.andExpect(model().attribute("notifications",
		        		org.hamcrest.collection.IsIterableWithSize.iterableWithSize(0)));
	}

	/*@Test
	public void noCleanNotificationWhenIdNoExists() throws Exception {
		int tableCode = 123;
		User wrongUser = new UserBuilder().waiter().email("wrong@mail.com").build();
		User waiter = new UserBuilder().waiter().build();
		this.mockMvc.perform(
				get("/"));
		this.mockMvc.perform(
                get("/askForHelp")
                        .sessionAttr(View.TABLE_TAG, tableCode))
                .andExpect(status().isOk());
		this.mockMvc.perform(
				post("/cleanNotification")
					.sessionAttr(View.EMAIL_TAG, wrongUser.getEmail())
					.param(CleanNotificationView.NOTIFICATION_ID, Integer.toString(tableCode)))
		.andExpect(status().isBadRequest());
		this.mockMvc.perform(
				post("/cleanNotification")
					.sessionAttr(View.EMAIL_TAG, waiter.getEmail())
					.param(CleanNotificationView.NOTIFICATION_ID, Integer.toString(tableCode)))
		.andExpect(status().isOk())
		.andExpect(model().attribute("notifications",
		        		org.hamcrest.collection.IsIterableWithSize.iterableWithSize(0)));
	}*/
}