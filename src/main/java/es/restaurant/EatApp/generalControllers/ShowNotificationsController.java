package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Employee;
import es.restaurant.EatApp.repositories.EmployeeDao;
import es.restaurant.EatApp.views.ShowNotificationView;

@Controller
public class ShowNotificationsController {

	@PostMapping("/showNotification")
	public String showNotifications(Model model, HttpServletRequest req, HttpServletResponse res) {
		ShowNotificationView view = new ShowNotificationView(model,req,res);
		Employee employee = EmployeeDao.getEmployeeDao().getEmployeeFromCache(view.getEmail());
		return view.interact(employee.getNotifications());
	}

}
