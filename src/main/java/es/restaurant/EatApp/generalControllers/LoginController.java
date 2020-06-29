package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import es.restaurant.EatApp.models.UserSql;
import es.restaurant.EatApp.models.repositories.UserSqlDao;
import es.restaurant.EatApp.views.LoginView;

@Controller
public class LoginController implements ControllerInterface {

	public LoginController() {
	}

	@PostMapping("/login")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {

		LoginView view = new LoginView(req, res, model);
		
		UserSql user = new UserSql(view.getEmail(), view.getPassword());
		if (new UserSqlDao().verifyUser(user)) {
			user = new UserSqlDao().getUser(user.getEmail()); // TODO fix
			return view.login(user);
		} else {
			return view.error();
		}
	}
}
