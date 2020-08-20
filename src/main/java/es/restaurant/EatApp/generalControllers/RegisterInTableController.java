package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Table;
import es.restaurant.EatApp.repositories.TableDao;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.RegisterInTableView;

@Controller
public class RegisterInTableController {

	@GetMapping("/registerInTable")
	public String preapreRegisterInTableForm(Model model, HttpServletRequest req, HttpServletResponse res) {
		RegisterInTableView view = new RegisterInTableView(model, req, res);
		return view.interact();
	}

	@PostMapping("/registerInTable")
	public String registerInTable(Model model, HttpServletRequest req, HttpServletResponse res) {
		RegisterInTableView view = new RegisterInTableView(model, req, res);
		Table table = TableDao.getTableDao().getTable(view.getTableCode()); 
		if(!table.isValid()) {
			return view.errorNotFound();
		}
		TableDao.getTableDao().linkUserToTable(UserDao.getUserDao().getUser(view.getEmail()).getId(), table);
		return view.register();
	}
}
