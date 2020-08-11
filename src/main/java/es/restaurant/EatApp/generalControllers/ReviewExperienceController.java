package es.restaurant.EatApp.generalControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.OrderToReview;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.repositories.OrderProductsDao;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.ReviewExperienceView;

@Controller
public class ReviewExperienceController {

	@GetMapping("/reviewExperience")
	public String showReviewExperienceOptions(Model model, HttpServletRequest req, HttpServletResponse res) {
		ReviewExperienceView view = new ReviewExperienceView(model, req, res);
		List<OrderToReview> orders = OrderProductsDao.getOrderProductsDao().getAllOrdersFromUser(UserDao.getUserDao().getUser(view.getEmail()).getId());
		//INTRODUCIR NOMBRE RESTAURANTE Y FECHA EN EL MODELO (Y EN LA BBDD) PARA MEJORAR COMPRENSIÓN
		return view.interact(orders);
	}


	@PostMapping("/showOrderToReview")
	public String showOrderToReview(Model model, HttpServletRequest req, HttpServletResponse res) {
		ReviewExperienceView view = new ReviewExperienceView(model, req, res);
		OrderToReview order = OrderProductsDao.getOrderProductsDao().getOrderToReviewById(view.getOrderId());
		if (order == null ) {
			view.errorIncorrectId();
		}
		return view.showOrderToReview(order);
	}
	
	@PostMapping("/reviewExperience")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res) {
		ReviewExperienceView view = new ReviewExperienceView(model, req, res);
		String review = view.getReview();
		if(review == null) {
			return view.noReviewError();
		}
		int id = view.getOrderId();
		OrderDao.getOrderDao().updateReview(review, id);
		return view.interact();
	}
	
}
