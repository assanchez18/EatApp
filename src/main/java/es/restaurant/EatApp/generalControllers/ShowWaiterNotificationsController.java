package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Waiter;
import es.restaurant.EatApp.models.repositories.WaiterDao;
import es.restaurant.EatApp.views.ShowNotificationView;

@Controller
public class ShowWaiterNotificationsController {
	
	@PostMapping("/showNotification")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		ShowNotificationView view = new ShowNotificationView(model,req,res);
		Waiter w = WaiterDao.getWaiterDao().getWaiter(view.getEmail());
		return view.interact(w.getNotifications());
		
	}
}
