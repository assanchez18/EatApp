package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class RegisterEmployeeView extends LoginView {

	private static final String FORM_VIEW = "registerEmployeeForm";
	public static final String REPEATED_PASSWORD_TAG = "password2";
	public static final String USER_TYPE_TAG = "userType";
	private static final String ERROR_USER_EXISTS_MSG = "El usuario que intentas crear ya existe";
	private static final String ERROR_PASSWORD_NOT_MATCH_MSG = "Las contraseñas introducidas son distintas";
	private static final String ERROR_UNABLE_TO_PERSIST_MSG = "No se ha podido guardar el usuario en la base de datos. Intentalo más tarde";
	
	public RegisterEmployeeView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public RegisterEmployeeView(HttpServletResponse res) {
		super(res);
	}

	public String showFrom() {
		setStatusOk();
		return FORM_VIEW;
	}
	
	public String interact() {
		setStatusOk();
		return MAIN_USER_VIEW;
	}
	
	public String errorUserExists() {
		return returnErrorWithMessage(ERROR_USER_EXISTS_MSG, HttpServletResponse.SC_BAD_REQUEST, ERROR_VIEW);
	}

	public String errorPasswordNotMatch() {
		return returnErrorWithMessage(ERROR_PASSWORD_NOT_MATCH_MSG, HttpServletResponse.SC_BAD_REQUEST, ERROR_VIEW);
	}
	
	public String errorUnableToPersistUser() {
		return returnErrorWithMessage(ERROR_UNABLE_TO_PERSIST_MSG, HttpServletResponse.SC_SERVICE_UNAVAILABLE, ERROR_VIEW);
	}

	public String getRepeatedPassword() {
		return this.request.getParameter(REPEATED_PASSWORD_TAG);
	}

	public int getUserType() {
		return Integer.parseInt(this.request.getParameter(USER_TYPE_TAG));
	}
}
