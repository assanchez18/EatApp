package es.restaurant.EatApp.GeneralControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.restaurant.EatApp.Models.User;
import es.restaurant.EatApp.Models.Repositories.UserRepository;

@Controller
public class LoginController {

	//private final UserRepository uRepository;
	
	@PostMapping("/login")
	public String control(Model model, @RequestParam String email, @RequestParam String password) {
	
		//Crear fachada para model?
		User user = new User(email, password);
		if (findUser(user)) {
			//createSession(user);
			model.addAttribute("password", password);
			return login();
		}
		else {
			return error(model);
		}
	}
	
	private boolean findUser(User user) {
		//return (uRepository.findUserByNameAndPassword(user.getName(), user.getPassword()) != null);
		return true;
	}

	private String login() {
		return "login";
	}
	
	private String error(Model model) {
		return "error";
	}

}
