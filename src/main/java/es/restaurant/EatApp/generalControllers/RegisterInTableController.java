package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import es.restaurant.EatApp.models.UserSql;
import es.restaurant.EatApp.models.repositories.UserSqlDao;
import es.restaurant.EatApp.views.LoginView;
import es.restaurant.EatApp.views.RegisterInTableView;

@Controller
public class RegisterInTableController implements ControllerInterface {

	@GetMapping("/registerInTable")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		return "registerInTable";
	}
	
	@PostMapping("/registerInTable")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		RegisterInTableView view = new RegisterInTableView(model, req, res);
		if(view.decode()) {
			return view.register();
		}
		return view.error();
	}
}
