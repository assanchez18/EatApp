package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Employee;
import es.restaurant.EatApp.repositories.EmployeeDao;
import es.restaurant.EatApp.views.CleanNotificationView;

@Controller
public class CleanNotificationController {

	@PostMapping("/cleanNotification")
	public String cleanNotifications(Model model, HttpServletRequest req, HttpServletResponse res) {
		CleanNotificationView view = new CleanNotificationView(model, req, res);
		Employee employee = EmployeeDao.getEmployeeDao().getEmployeeByEmail(view.getEmail());
		employee.completeNotification(view.getNotificationId());
		return view.getCleanNotifications(employee.getNotifications());
	}
}