package es.restaurant.EatApp.GeneralControllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	public LogoutController() {
	}

	@GetMapping("/logout")
	public String control(Model model, HttpSession session) {
		deleteSession(session);
		return Logout();
	}

	private void deleteSession(HttpSession session) {
		session.invalidate();
	}

	private String Logout() {
		return "/";
	}

	private String error(Model model) {
		return "error";
	}

}
