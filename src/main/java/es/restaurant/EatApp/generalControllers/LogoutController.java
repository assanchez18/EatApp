package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.models.UserSql;
import es.restaurant.EatApp.models.repositories.UserSqlDao;
import es.restaurant.EatApp.views.LogoutView;

@Controller
public class LogoutController implements ControllerInterface {

	public LogoutController() {
	}

	@GetMapping("/logout")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		LogoutView view = new LogoutView(req, res, model);
		UserSql user = new UserSqlDao().getUser(view.getEmail());
		return view.logout(user);
	}
}
