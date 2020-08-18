package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.User;

public class RemoveEmployeeView extends LoginView {

	private static final String VIEW_REMOVE_EMPLOYEE = "removeEmployeeForm";
	private static final String TAG_EMPLOYEES = "employees";
	public static final String TAG_USER_TO_REMOVE = "userToRemove";
	private static final String MSG_WRONG_USER_ERROR = "El usuario que quieres eliminar no existe";

	public RemoveEmployeeView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public String showForm(List<User> employees) {
		this.model.addAttribute(TAG_EMPLOYEES, employees);
		setStatusOk();
		return VIEW_REMOVE_EMPLOYEE;
	}

	public String errorInvalidUser() {
		return returnErrorWithMessageAndErrorCode(MSG_WRONG_USER_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}

	public String interact() {
		setStatusOk();
		return VIEW_MAIN_USER;
	}
	
	public String getUserToRemove() {
		return this.request.getParameter(TAG_USER_TO_REMOVE);
	}
	
}
