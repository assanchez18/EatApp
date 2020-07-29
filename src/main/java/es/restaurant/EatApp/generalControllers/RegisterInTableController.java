package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.repositories.TableDao;
import es.restaurant.EatApp.views.RegisterInTableView;

@Controller
public class RegisterInTableController {

	@GetMapping("/registerInTable")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		RegisterInTableView view = new RegisterInTableView(model, req, res);
		return view.redirect();
	}

	@PostMapping("/registerInTable")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		RegisterInTableView view = new RegisterInTableView(model, req, res);
		int code = view.getCode();
		TableDao dao = TableDao.getTableDao();
		if(dao.getTable(code) != null) {
			return view.register();
		}
		return view.errorNotFound();
	}
}
