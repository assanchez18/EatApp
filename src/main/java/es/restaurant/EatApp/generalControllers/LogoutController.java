package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.views.LogoutView;

@Controller
public class LogoutController {

	@GetMapping("/logout")
	public String logout(Model model, HttpServletRequest req, HttpServletResponse res) {
		LogoutView view = new LogoutView(req);
		return view.logout();
	}
}
