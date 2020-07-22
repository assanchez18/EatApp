package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.views.CreateNewOrderView;

@Controller
public class CreateNewOrderController {

	@GetMapping("/createOrder")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {

		CreateNewOrderView view = new CreateNewOrderView(model, req, res);
		return view.redirect();
	}
	
}
