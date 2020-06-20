package es.restaurant.EatApp.GeneralControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.Models.facades.WebMediator;

@Controller
public class LogoutController implements ControllerInterface {

	public LogoutController() {
	}

	@GetMapping("/logout")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		WebMediator mediator = new WebMediator(req, res, model);
		mediator.sessionInvalidate();
		return Logout();
	}

	private String Logout() {
		return "/";
	}
}
