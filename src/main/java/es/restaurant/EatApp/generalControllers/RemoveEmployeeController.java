package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.repositories.EmployeeDao;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.RemoveEmployeeView;

@Controller
public class RemoveEmployeeController {
	
	@GetMapping("/removeEmployee")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		RemoveEmployeeView view = new RemoveEmployeeView(model, req, res);
		return view.showForm(EmployeeDao.getEmployeeDao().getAllEmployeesBut(view.getEmail()));
	}

	@PostMapping("/removeEmployee")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res) {
		RemoveEmployeeView view = new RemoveEmployeeView(model, req, res);
		User user = UserDao.getUserDao().getUser(view.getUserToRemove());
		if(user == null) {
			return view.errorInvalidUser();
		}
		UserDao.getUserDao().deleteUser(user);
		return view.interact();
	}
}
