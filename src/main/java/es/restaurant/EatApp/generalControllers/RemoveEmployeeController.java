package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Employee;
import es.restaurant.EatApp.repositories.EmployeeDao;
import es.restaurant.EatApp.views.RemoveEmployeeView;

@Controller
public class RemoveEmployeeController {
	
	@GetMapping("/removeEmployee")
	public String prepareRemoveEmployeeForm(Model model, HttpServletRequest req, HttpServletResponse res) {
		RemoveEmployeeView view = new RemoveEmployeeView(model, req, res);
		return view.getShowEmployees(EmployeeDao.getEmployeeDao().getAllEmployeesBut(view.getEmail()));
	}

	@PostMapping("/removeEmployee")
	public String removeEmployee(Model model, HttpServletRequest req, HttpServletResponse res) {
		RemoveEmployeeView view = new RemoveEmployeeView(model, req, res);
		Employee employee = EmployeeDao.getEmployeeDao().getEmployeeByEmail(view.getEmployeToRemoveEmail());
		if (!EmployeeDao.getEmployeeDao().removeEmployee(employee)) {
			return view.getErrorRemovingEmployee();
		}
		return view.getRemoveEmployee();
	}
}
