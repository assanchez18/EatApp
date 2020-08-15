package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public abstract class RegisterView extends LoginView {

	public static final String TAG_REPEATED_PASSWORD = "repeatedPassword";
	protected static final String VIEW_FORM = "registerForm";
	protected static final String TAG_TITLE = "title";
	private static final String TAG_REGISTER_USER = "registerUser";
	
	private static final String MSG_USER_EXISTS_ERROR = "El usuario que intentas crear ya existe";
	private static final String MSG_PASSWORD_NOT_MATCH_ERROR = "Las contraseñas introducidas son distintas";
	private static final String MSG_UNABLE_TO_PERSIST_ERROR = "No se ha podido crear el usuario en el sistema. Intentalo más tarde";
	
	public RegisterView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public String showFrom() {
		setStatusOk();
		this.model.addAttribute(TAG_REGISTER_USER, true);
		updateModel();
		return VIEW_FORM;
	}
	
	protected abstract void updateModel();
	public abstract int getUserType();
	
	
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

}
