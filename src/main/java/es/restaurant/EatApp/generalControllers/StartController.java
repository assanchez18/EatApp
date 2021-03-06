package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.models.Employee;
import es.restaurant.EatApp.repositories.TableDao;
import es.restaurant.EatApp.repositories.WaiterDao;
import es.restaurant.EatApp.views.StartView;

@Controller
public class StartController {

	@GetMapping("/")
	public String start(Model model, HttpServletRequest req, HttpServletResponse res) {
		StartView view = new StartView(req, res);	
		waitersObserveTables();
		return view.interact();
	}
	
	private void waitersObserveTables() {
		for (Employee waiter : WaiterDao.getWaiterDao().getWaiters()) {
			waiter.addObserver(TableDao.getTableDao().getTables());
		}
	}
}
