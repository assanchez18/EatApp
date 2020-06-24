package es.restaurant.EatApp.GeneralControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import es.restaurant.EatApp.Models.UserSql;
import es.restaurant.EatApp.Models.Repositories.UserSqlDao;
import es.restaurant.EatApp.Models.facades.WebMediator;

@Controller
public class LoginController implements ControllerInterface {

	public LoginController() {
	}

	@PostMapping("/login")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {

		WebMediator mediator = new WebMediator(req, res, model);
		
		UserSql user = new UserSql(mediator.getEmail(), mediator.getPassword());
		if (findUser(user)) {
			mediator.modelAddEmail();
			mediator.sessionAddEmail();
			mediator.responseSetStatusOk();
			mediator.sessionAddType("Commensal");
			mediator.modelAddType("Commensal");
			return login();
		}
		else {
			mediator.responseSetStatusLoginError();
			return error();
		}
	}


	private boolean findUser(UserSql user) {
		UserSqlDao dao = new UserSqlDao();
		return dao.verifyUser(user);
	}

	private String login() {
		return "mainUserView";
	}

	private String error() {
		return "error";
	}

}
