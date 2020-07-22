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

@RunWith(SpringRunner.class)
public class AskForHelpTest {

	private StartController startController;
	private AskForHelpController askController;
	private ShowWaiterNotificationsController showController;
	private MockMvc mockMvcAsk;
	private final String TABLE_TAG = "table";
	
	@Before
	public void setup() {
		this.startController = new StartController();
		this.askController = new AskForHelpController();
		this.showController = new ShowWaiterNotificationsController();
		MockitoAnnotations.initMocks(this);
		this.mockMvcAsk = MockMvcBuilders.standaloneSetup(this.startController,
				                                          this.askController,
													      this.showController).build();
		
	}
	
	@Test
    public void askForHelpTest() throws Exception {
		this.mockMvcAsk.perform(
				get("/"));
		this.mockMvcAsk.perform(
                get("/askForHelp")
                        .sessionAttr(TABLE_TAG, "123"))
                .andExpect(status().isOk());
        this.mockMvcAsk.perform(
                post("/showNotification")
                		.sessionAttr(TABLE_TAG, "123")
                		.sessionAttr("email", "waiter@waiter.com"))
        		.andExpect(status().isOk())
        		.andExpect(model().attribute("notifications",
        		org.hamcrest.collection.IsIterableWithSize.iterableWithSize(1)));
    }

}
