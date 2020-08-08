package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserType.userType;
import es.restaurant.EatApp.models.Employee;
import es.restaurant.EatApp.repositories.CookDao;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.repositories.WaiterDao;
import es.restaurant.EatApp.views.ShowNotificationView;

@Controller
public class ShowWaiterNotificationsController {

	@PostMapping("/showNotification")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		ShowNotificationView view = new ShowNotificationView(model,req,res);
		User user = UserDao.getUserDao().getUser(view.getEmail());
		Employee employee;
		if(user.getUserType().getType() == userType.WAITER) {
			employee = WaiterDao.getWaiterDao().getWaiter(view.getEmail());
		} else {
			employee = CookDao.getCookDao().getCook(view.getEmail());
		} // TODO Needs refactor, visitor??
		return view.interact(employee.getNotifications());
	}

}
