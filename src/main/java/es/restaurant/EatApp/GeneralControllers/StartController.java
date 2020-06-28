package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class StartController implements ControllerInterface {

	@GetMapping("/")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		return start();
	}
	
	private String start() {
		return "index";
	}

}
