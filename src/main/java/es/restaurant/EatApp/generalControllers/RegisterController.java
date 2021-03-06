package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.RegisterView;
import es.restaurant.EatApp.views.RegisterViewBuilder;

@Controller
public class RegisterController {
	
	@GetMapping("/register")
	public String showRegisterFrom(Model model, HttpServletRequest req, HttpServletResponse res) {
		return new RegisterViewBuilder().build(model, req, res).showRegisterFrom();
	}

	@PostMapping("/register")
	public String register(Model model, HttpServletRequest req, HttpServletResponse res) {
		RegisterView view = new RegisterViewBuilder().build(model, req, res);
		if (UserDao.getUserDao().getUser(view.getEmail()).isValid()) {
			return view.errorUserExists();
		}
		if (!verifyPasswordMatchs(view.getPassword(), view.getRepeatedPassword())) {
			return view.errorPasswordNotMatch();
		}
		User correctUser = new User(view.getEmail(), view.getPassword(), view.getUserType());
		if(!UserDao.getUserDao().insertNewUser(correctUser)) {
			return view.errorUnableToPersistUser();
		}
		return view.registry();
	}

	private boolean verifyPasswordMatchs(String password1, String repeatedPass) {
		return (password1.compareTo(repeatedPass) == 0);
	}

}
