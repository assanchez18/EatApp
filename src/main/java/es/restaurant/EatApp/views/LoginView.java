package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import es.restaurant.EatApp.models.User;

public class LoginView extends View {

	public static final String PASSWORD_TAG = "password";
	private static final String LOGIN_ERROR_MSG = "El usuario o la contraseña son incorrectos";
	private static final String TYPE_TAG = "type";
	
	public LoginView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String login(User user) {
		this.session.setAttribute(EMAIL_TAG, this.getEmail());
		this.session.setAttribute(TYPE_TAG, user.getUserType().getTypeName());
		setStatusOk();
		return MAIN_USER_VIEW;
	}

	public String errorUnauthorized() {
		return this.returnErrorWithMessage(LOGIN_ERROR_MSG, HttpServletResponse.SC_UNAUTHORIZED, ERROR_VIEW);
	}

	public String getPassword() {
		return this.request.getParameter(PASSWORD_TAG);
	}

	@Override
	public String getEmail() {
		return this.request.getParameter(EMAIL_TAG);
	}
}
