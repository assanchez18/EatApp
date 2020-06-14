package es.restaurant.EatApp.GeneralControllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class StartController {

	@GetMapping("/")
	public String control(Model model) {
		return start();
	}
	
	private String start() {
		return "index";
	}
}
