package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.RegisterEmployeeView;

@Controller
public class RegisterEmployeeController {

	@GetMapping("/registerEmployee")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		RegisterEmployeeView view = new RegisterEmployeeView(res);
		return view.showFrom();
	}
	
	@PostMapping("/registerEmployee")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res) {
		RegisterEmployeeView view = new RegisterEmployeeView(model, req, res);
		User user = UserDao.getUserDao().getUser(view.getReqEmail());
		if (user != null) {
			return view.errorUserExists();
		}
		if (!verifyPasswordMatchs(view.getPassword(), view.getRepeatedPassword())) {
			return view.errorPasswordNotMatch();
		}
		User correctUser = new User(view.getReqEmail(), view.getPassword(), view.getUserType());
		if(!UserDao.getUserDao().insert(correctUser)) {
			return view.errorUnableToPersistUser();
		}
		return view.interact();
	}

	public boolean verifyPasswordMatchs(String password1, String repeatedPass) {
		return (password1.compareTo(repeatedPass) == 0);
	}
}