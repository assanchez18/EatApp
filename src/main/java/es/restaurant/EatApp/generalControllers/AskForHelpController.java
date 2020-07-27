package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.models.Table;
import es.restaurant.EatApp.repositories.TableDao;
import es.restaurant.EatApp.views.AskForHelpView;

@Controller
public class AskForHelpController implements ControllerInterface {

	@GetMapping("/askForHelp")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		AskForHelpView view = new AskForHelpView(model, req, res); 
		Table table = TableDao.getTableDao().getTable(view.getTableCode());
		if (table == null) {
			return view.wrongTableCode();
		}
		table.askForHelp();
		return view.interact();
	}
}
