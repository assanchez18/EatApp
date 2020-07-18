package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.models.Waiter;
import es.restaurant.EatApp.repositories.TableDao;
import es.restaurant.EatApp.repositories.WaiterDao;
import es.restaurant.EatApp.views.StartView;

@Controller
public class StartController implements ControllerInterface {

	@GetMapping("/")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		StartView view = new StartView(req, res);	
		waitersObserveTables();
		return view.interact();
	}
	
	private void waitersObserveTables() {
		for (Waiter w : WaiterDao.getWaiterDao().getWaiters()) {
			w.addObserver(TableDao.getTableDao().getTables());
		}
	}
}
