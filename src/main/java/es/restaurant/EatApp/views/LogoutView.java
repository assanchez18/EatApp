package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;


import es.restaurant.EatApp.models.facades.HttpServletRequestFacade;
import es.restaurant.EatApp.models.facades.HttpServletResponseFacade;
import es.restaurant.EatApp.models.facades.SessionFacade;

public class LogoutView {
	
	private HttpServletRequestFacade request;
	private HttpServletResponseFacade response;
	private SessionFacade session;

	private Model model;
	
	private static final String EMAIL_TAG = "email";
	private static final String TYPE_TAG = "userType";
	
	public LogoutView(HttpServletRequest req, HttpServletResponse res, Model model) {
		this.request = new HttpServletRequestFacade(req);
		this.response = new HttpServletResponseFacade(res);
		this.session = new SessionFacade(req.getSession());
		this.model = model;
	}

	public String logout() {
		//get User type form DB
		//Set attribute type as false
		//ser mail as false? --> check html
		this.session.removeAttribute(EMAIL_TAG);
		this.session.removeAttribute(TYPE_TAG);
		
		this.session.invalidate();
		//remove useless data from model and session?
		
		return "/";
	}
	
	public String getEmail() {
		return this.request.getParameter(EMAIL_TAG);
	}
}
