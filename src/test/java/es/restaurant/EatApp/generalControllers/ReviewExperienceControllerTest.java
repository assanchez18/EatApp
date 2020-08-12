package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import es.restaurant.EatApp.views.ReviewExperienceView;
import es.restaurant.EatApp.views.View;

@RunWith(SpringRunner.class)
public class ReviewExperienceControllerTest {
	private ReviewExperienceController controller;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.controller = new ReviewExperienceController();
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
    public void showOrdersToReviewGetTest() throws Exception {
        User commensal = new UserBuilder().commensal().build();
		this.mockMvc.perform(
                get("/reviewExperience")
                .sessionAttr(View.EMAIL_TAG, commensal.getEmail()))
                .andExpect(status().isOk());
    }
	
	@Test
    public void showOrderReviewPostTest() throws Exception {
        String orderId = "1";
		this.mockMvc.perform(
                post("/showOrderReview")
                .param(ReviewExperienceView.ORDER_ID_TAG, orderId))
                .andExpect(status().isOk());
		
		String wrongOrderId = "-1";
		this.mockMvc.perform(
				post("/showOrderReview")
                .param(ReviewExperienceView.ORDER_ID_TAG, wrongOrderId))
                .andExpect(status().isBadRequest());
    }

	@Test
    public void reviewExperiencePostTest() throws Exception {
        String reviewTest = "newReview";
        String orderId = "1";
		this.mockMvc.perform(
                post("/reviewExperience")
                .param(ReviewExperienceView.REVIEW_TAG, reviewTest)
                .param(ReviewExperienceView.ORDER_ID_TAG, orderId))
                .andExpect(status().isOk());
		//restore
		String emptyReview = "";
		this.mockMvc.perform(
				post("/reviewExperience")
                .param(ReviewExperienceView.REVIEW_TAG, emptyReview)
                .param(ReviewExperienceView.ORDER_ID_TAG, orderId))
                .andExpect(status().isOk());
    }
}
