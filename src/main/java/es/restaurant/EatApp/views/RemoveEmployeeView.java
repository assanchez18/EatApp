package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.User;

public class RemoveEmployeeView extends LoginView {

	private static final String REMOVE_EMPLOYEE_VIEW = "removeEmployeeForm";
	private static final String EMPLOYEES_TAG = "employees";
	public static final String USER_TO_REMOVE_TAG = "userToRemove";
	private static final String ERROR_WRONG_USER_MSG = "El usuario que quieres eliminar no existe";

	public RemoveEmployeeView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public String showForm(List<User> employees) {
		this.model.addAttribute(EMPLOYEES_TAG, employees);
		setStatusOk();
		return REMOVE_EMPLOYEE_VIEW;
	}

	public String errorInvalidUser() {
		return returnErrorWithMessage(ERROR_WRONG_USER_MSG, HttpServletResponse.SC_BAD_REQUEST, ERROR_VIEW);
	}

	public String interact() {
		setStatusOk();
		return MAIN_USER_VIEW;
	}
	
	public String getUserToRemove() {
		return this.request.getParameter(USER_TO_REMOVE_TAG);
	}
	
}
