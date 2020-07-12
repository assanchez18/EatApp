package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import es.restaurant.EatApp.models.User;

public class LoginView extends View {

	private static final String TYPE_TAG = "type";
	private static final String PASSWORD_TAG = "password";
	
	public LoginView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String login(User user) {
		this.session.addAttribute(EMAIL_TAG, this.getEmail());
		this.session.addAttribute(TYPE_TAG, user.getUserType().getTypeName());
		this.response.setStatusOk();
		return "mainUserView";
	}

	public String error() {
		this.session.invalidate();
		this.model.addAttributeError("El usuario o la contraseña son incorrectos");
		this.response.setStatusUnauthorizedLoginError();
		return "index";
	}

	public String getPassword() {
		return this.request.getParameter(PASSWORD_TAG);
	}
	
	@Override
	public String getEmail() {
		return this.request.getParameter(EMAIL_TAG);
	}

}
