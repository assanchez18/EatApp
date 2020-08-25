package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.User;

public class RemoveEmployeeView extends LoginView {

	public static final String TAG_USER_TO_REMOVE = "userToRemove";
	private final String VIEW_REMOVE_EMPLOYEE = "removeEmployeeForm";
	private final String TAG_EMPLOYEES = "employees";
	private final String MSG_DB_ERROR = "The DB is not working correctly.";

	public RemoveEmployeeView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public String getShowEmployees(List<User> employees) {
		this.model.addAttribute(TAG_EMPLOYEES, employees);
		setStatusOk();
		return VIEW_REMOVE_EMPLOYEE;
	}

	public String getRemoveEmployee() {
		setStatusOk();
		return VIEW_MAIN_USER;
	}
	
	public String getEmployeToRemoveEmail() {
		return this.request.getParameter(TAG_USER_TO_REMOVE);
	}

	public String getErrorRemovingEmployee() {
		return returnErrorWithMessageAndErrorCode(MSG_DB_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}
	
}
