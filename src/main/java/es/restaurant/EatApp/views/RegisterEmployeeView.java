package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class RegisterEmployeeView extends LoginView {

	private static final String VIEW_FORM = "registerEmployeeForm";
	public static final String TAG_REPEATED_PASSWORD = "password2";
	public static final String TAG_USER_TYPE = "userType";
	private static final String MSG_USER_EXISTS_ERROR = "El usuario que intentas crear ya existe";
	private static final String MSG_PASSWORD_NOT_MATCH_ERROR = "Las contraseñas introducidas son distintas";
	private static final String MSG_UNABLE_TO_PERSIST_ERROR = "No se ha podido guardar el usuario en la base de datos. Intentalo más tarde";
	
	public RegisterEmployeeView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public RegisterEmployeeView(HttpServletResponse res) {
		super(res);
	}

	public String showFrom() {
		setStatusOk();
		return VIEW_FORM;
	}
	
	public String interact() {
		setStatusOk();
		return VIEW_MAIN_USER;
	}
	
	public String errorUserExists() {
		return returnErrorWithMessage(MSG_USER_EXISTS_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}

	public String errorPasswordNotMatch() {
		return returnErrorWithMessage(MSG_PASSWORD_NOT_MATCH_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}
	
	public String errorUnableToPersistUser() {
		return returnErrorWithMessage(MSG_UNABLE_TO_PERSIST_ERROR, HttpServletResponse.SC_SERVICE_UNAVAILABLE, VIEW_ERROR);
	}

	public String getRepeatedPassword() {
		return this.request.getParameter(TAG_REPEATED_PASSWORD);
	}

	public int getUserType() {
		return Integer.parseInt(this.request.getParameter(TAG_USER_TYPE));
	}
}
