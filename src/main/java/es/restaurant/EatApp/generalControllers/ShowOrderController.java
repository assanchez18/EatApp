package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.views.ShowOrderView;

@Controller
public class ShowOrderController extends OrderController {

	private ShowOrderView view;
	
	@GetMapping("/showNewOrder")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new ShowOrderView(model, req, res);
		// TODO get from database, now is failing
		return view.interactPost();	
	}


	@PostMapping("/showNewOrder")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new ShowOrderView(model, req, res);
		return createOrder(this.view);	
	}

	protected String interact() {
		return this.view.interactPost();
	}
}
