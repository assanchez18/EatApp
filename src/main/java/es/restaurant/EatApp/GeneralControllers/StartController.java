package es.restaurant.EatApp.GeneralControllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class StartController {
	@GetMapping("/")
	public String start(Model model) {		
		return "index";
	}
}
