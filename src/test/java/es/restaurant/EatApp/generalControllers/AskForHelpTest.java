package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.views.CleanNotificationView;
import es.restaurant.EatApp.views.helpers.EmailHelperView;
import es.restaurant.EatApp.views.helpers.TableHelperView;

@RunWith(SpringRunner.class)
public class AskForHelpTest {

	private StartController startController;
	private AskForHelpController askController;
	private ShowNotificationsController showController;
	private CleanNotificationController cleanController;
	private MockMvc mockMvcAsk;
	private int tableCode;

	@Before
	public void setup() {
		this.tableCode = 123;
		this.startController = new StartController();
		this.askController = new AskForHelpController();
		this.showController = new ShowNotificationsController();
		this.cleanController = new CleanNotificationController();
		MockitoAnnotations.initMocks(this);
		this.mockMvcAsk = MockMvcBuilders.standaloneSetup(this.startController,
														  this.askController,
														  this.showController,
														  this.cleanController).build();
	}

	@After
	public void tearDown() throws Exception {
		User w = new UserBuilder().waiter().build();
		this.mockMvcAsk.perform(
				post("/cleanNotification")
					.sessionAttr(EmailHelperView.TAG_EMAIL, w.getEmail())
					.param(CleanNotificationView.TAG_NOTIFICATION_ID, Integer.toString(this.tableCode)))
				.andExpect(status().isOk())
				.andExpect(model().attribute("notifications",
						org.hamcrest.collection.IsIterableWithSize.iterableWithSize(0)));
	}

	@Test
	public void askForHelpTest() throws Exception {
		this.mockMvcAsk.perform(
				get("/"));
		this.mockMvcAsk.perform(
				get("/askForHelp")
					.sessionAttr(TableHelperView.TAG_TABLE, this.tableCode))
				.andExpect(status().isOk());
		this.mockMvcAsk.perform(
				post("/showNotification")
					.sessionAttr(TableHelperView.TAG_TABLE, this.tableCode)
					.sessionAttr(EmailHelperView.TAG_EMAIL, "waiter@waiter.com"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("notifications",
						org.hamcrest.collection.IsIterableWithSize.iterableWithSize(1)));
	}
	
	@Test
	public void askForHelpWrongTableTest() throws Exception {
		this.mockMvcAsk.perform(
				get("/"));
		this.mockMvcAsk.perform(
				get("/askForHelp")
					.sessionAttr(TableHelperView.TAG_TABLE, -1))
				.andExpect(status().isBadRequest());
	}
}
