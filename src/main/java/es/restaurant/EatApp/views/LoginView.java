package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.UserSql;
import es.restaurant.EatApp.models.facades.HttpServletRequestFacade;
import es.restaurant.EatApp.models.facades.HttpServletResponseFacade;
import es.restaurant.EatApp.models.facades.SessionFacade;

public class LoginView {

	private HttpServletRequestFacade request;
	private HttpServletResponseFacade response;
	private SessionFacade session;

	private Model model;

	private static final String EMAIL_TAG = "email";
	private static final String TYPE_TAG = "type";
	private static final String PASSWORD_TAG = "password";
	public static final String ERROR_NOT_FOUND = "User Not Found";
	public static final String ERROR_UNAUTHORIZED =  "User Unauthorized";
	
	public LoginView(HttpServletRequest req, HttpServletResponse res, Model model) {
		this.request = new HttpServletRequestFacade(req);
		this.response = new HttpServletResponseFacade(res);
		this.session = new SessionFacade(req.getSession());
		this.model = model;
	}
	
	public String login(UserSql user) {
		this.session.addAttribute(EMAIL_TAG,this.getEmail());
		this.session.addAttribute(TYPE_TAG, user.getUserType().getTypeName());
		this.response.setStatusOk();
		return "mainUserView";
	}

	public String error(String error) {
		this.session.invalidate();
		if(error == ERROR_NOT_FOUND) {
			this.model.addAttribute("error", "El usuario no está registrado");
			this.response.setStatusNotFoundLoginError();
		} else {
			this.model.addAttribute("error", "Contraseña incorrecta");
			this.response.setStatusUnauthorizedLoginError();
		}
		return "index";
	}

	public String getEmail() {
		return this.request.getParameter(EMAIL_TAG);
	}

	public String getPassword() {
		return this.request.getParameter(PASSWORD_TAG);
	}

}
