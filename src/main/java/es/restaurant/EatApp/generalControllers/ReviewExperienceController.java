package es.restaurant.EatApp.generalControllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.OrderReview;
import es.restaurant.EatApp.repositories.OrderReviewDao;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.ReviewExperienceView;

@Controller
public class ReviewExperienceController {

	@GetMapping("/reviewExperience")
	public String showReviewExperienceOptions(Model model, HttpServletRequest req, HttpServletResponse res) {
		ReviewExperienceView view = new ReviewExperienceView(model, req, res);
		List<OrderReview> orders = new ArrayList<OrderReview>();
		if(view.isOrderInProgress()) {
			Order order = view.getOrder();
			orders.add(new OrderReview(order.getId(), order.getUserId()));
		}
		else {
			orders = OrderReviewDao.getOrderReviewDao().getAllOrdersWithoutReviewFromUser(UserDao.getUserDao().getUser(view.getEmail()).getId());
		}
		return view.interact(orders);
	}
	
	@PostMapping("/reviewExperience")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res) {
		ReviewExperienceView view = new ReviewExperienceView(model, req, res);
		String review = view.getReview();
		if(review == null) {
			return view.noReviewError();
		}
		OrderReviewDao.getOrderReviewDao().updateReview(review, view.getOrderId());
		return view.interact();
	}
	
}
