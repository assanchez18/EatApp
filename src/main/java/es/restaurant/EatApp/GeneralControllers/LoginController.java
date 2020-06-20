package es.restaurant.EatApp.GeneralControllers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import es.restaurant.EatApp.Models.User;
import es.restaurant.EatApp.Models.Repositories.UserRepository;
import es.restaurant.EatApp.Models.facades.WebMediator;

@Controller
public class LoginController implements ControllerInterface {

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
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {

		WebMediator mediator = new WebMediator(req, res, model);
		
		User user = new User(mediator.getEmail(), mediator.getPassword());
		if (findUser(user)) {
			mediator.modelAddEmail();
			mediator.sessionAddEmail();
			mediator.responseSetStatusOk();
			return login();
		}
		else {
			mediator.responseSetStatusLoginError();
			return error();
		}
	}


	private boolean findUser(User user) {
		return (this.userRepository.findUserByNameAndPassword(user.getEmail(), user.getPassword()) != null);
	}

	private String login() {
		return "mainUserView";
	}

	private String error() {
		return "error";
	}

}
