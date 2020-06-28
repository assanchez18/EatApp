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
	private static final String TYPE_TAG = "userType";
	private static final String PASSWORD_TAG = "password";
	
	public LoginView(HttpServletRequest req, HttpServletResponse res, Model model) {
		this.request = new HttpServletRequestFacade(req);
		this.response = new HttpServletResponseFacade(res);
		this.session = new SessionFacade(req.getSession());
		this.model = model;
	}
	
	public String login(UserSql user) {
		this.model.addAttribute(EMAIL_TAG, this.getEmail());
		this.session.addAttribute(EMAIL_TAG,this.getEmail());
		//refactor tagType to cleaner way
		this.session.addAttribute(TYPE_TAG+user.getUserType().getTypeName(),true);
		this.model.addAttribute(TYPE_TAG+user.getUserType().getTypeName(),true);
		this.response.setStatusOk();
		return "mainUserView";
	}

	public String error() {
		this.session.invalidate();
		return "error";
	}

	public String getEmail() {
		return this.request.getParameter(EMAIL_TAG);
	}

	public String getPassword() {
		return this.request.getParameter(PASSWORD_TAG);
	}

}
