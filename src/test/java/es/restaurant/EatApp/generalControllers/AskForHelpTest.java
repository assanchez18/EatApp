package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

	private AskForHelpController controller;
	private MockMvc mockMvc;
	private final String TABLE_TAG = "table";
	
	@Before
	public void setup() {
		this.controller = new AskForHelpController();

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
    public void askForHelpTest() throws Exception {
        this.mockMvc.perform(
                get("/askForHelp")
                        .sessionAttr(TABLE_TAG, "123"))
                .andExpect(status().isOk());
    }

}
