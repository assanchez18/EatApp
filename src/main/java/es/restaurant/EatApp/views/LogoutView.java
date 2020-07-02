package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.UserSql;
import es.restaurant.EatApp.models.facades.HttpServletRequestFacade;
import es.restaurant.EatApp.models.facades.HttpServletResponseFacade;
import es.restaurant.EatApp.models.facades.SessionFacade;

public class LogoutView {
	
	private HttpServletRequestFacade request;
	private HttpServletResponseFacade response;
	private SessionFacade session;

	private Model model;
	
	private static final String EMAIL_TAG = "email";
	
	public LogoutView(HttpServletRequest req, HttpServletResponse res, Model model) {
		this.request = new HttpServletRequestFacade(req);
		this.response = new HttpServletResponseFacade(res);
		this.session = new SessionFacade(req.getSession());
		this.model = model;
	}

	public String logout(UserSql user) {
		this.session.invalidate();		
		return "/index";
	}
	
	public String getEmail() {
		return this.session.getAttribute(EMAIL_TAG);
	}
}
