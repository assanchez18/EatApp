package es.restaurant.EatApp.GeneralControllers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.restaurant.EatApp.Models.User;
import es.restaurant.EatApp.Models.Repositories.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	public LoginController() {
	}

	public LoginController(UserRepository u) {
		this.userRepository = u;
	}

	@PostConstruct
	public void init() {
		userRepository.save(new User("admin@admin","admin"));
	}

	@PostMapping("/login")
	public String control(Model model, @RequestParam String email, @RequestParam String password, HttpSession session) {
		User user = new User(email, password);
		if (findUser(user)) {
			createSession(session, user);
			model.addAttribute("email", email);
			return login();
		}
		else {
			return error(model);
		}
	}

	private void createSession(HttpSession session, User user) {
		session.setAttribute("email", user.getEmail());
		session.setMaxInactiveInterval(600); //In seconds
	}

	private boolean findUser(User user) {
		return (this.userRepository.findUserByNameAndPassword(user.getEmail(), user.getPassword()) != null);
	}

	private String login() {
		return "mainUserView";
	}

	private String error(Model model) {
		return "error";
	}

}
