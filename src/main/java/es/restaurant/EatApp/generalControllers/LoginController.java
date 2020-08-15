package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.LoginView;

@Controller
public class LoginController {

	@PostMapping("/login")
	public String login(Model model, HttpServletRequest req, HttpServletResponse res) {
		LoginView view = new LoginView(model, req, res);
		User user = new User(view.getEmail(), view.getPassword());
		if(!UserDao.getUserDao().isUserCorrect(user)) {
			return view.errorUnauthorized();	
		}
		return view.login(UserDao.getUserDao().getUser(user.getEmail()));
	}
}
