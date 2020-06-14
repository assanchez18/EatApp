package es.restaurant.EatApp.GeneralControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.restaurant.EatApp.Models.User;
import es.restaurant.EatApp.Models.Repositories.UserRepository;

@Controller
public class LoginController {

	private UserRepository userRepository;
	
	public LoginController(UserRepository u) {
		this.userRepository = u;
	}	
	
	@PostMapping("/login")
	public String control(Model model, @RequestParam String email, @RequestParam String password) {
	
		User user = new User(email, password);
		if (findUser(user)) {
			
			//TODO: handle session creation
			//createSession(user);
			model.addAttribute("password", password);
			return login();
		}
		else {
			return error(model);
		}
	}
	
	private boolean findUser(User user) {
		return (this.userRepository.findUserByNameAndPassword(user.getEmail(), user.getPassword()) != null);
	}

	private String login() {
		return "login";
	}
	
	private String error(Model model) {
		return "error";
	}
	
}
