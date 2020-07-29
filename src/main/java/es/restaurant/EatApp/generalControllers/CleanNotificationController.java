package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Waiter;
import es.restaurant.EatApp.repositories.WaiterDao;
import es.restaurant.EatApp.views.CleanNotificationView;

@Controller
public class CleanNotificationController {

	@PostMapping("/cleanNotification")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		CleanNotificationView view = new CleanNotificationView(model, req, res);
		Waiter waiter = WaiterDao.getWaiterDao().getWaiter(view.getEmail());
		if (waiter == null) {
			return view.errorNoWaiter();
		}
		waiter.completeNotification(view.getNotificationId());
		return view.interact(waiter.getNotifications());
	}
}