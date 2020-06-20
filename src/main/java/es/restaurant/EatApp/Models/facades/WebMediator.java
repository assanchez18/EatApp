package es.restaurant.EatApp.Models.facades;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class WebMediator {

	private HttpServletRequestFacade request;
	private HttpServletResponseFacade response;
	private SessionFacade session;

	private Model model;

	private static final String EMAIL_TAG = "email";
	private static final String PASSWORD_TAG = "password";
	
	public WebMediator(HttpServletRequest req, HttpServletResponse res, Model model) {
		this.request = new HttpServletRequestFacade(req);
		this.response = new HttpServletResponseFacade(res);
		this.session = new SessionFacade(req.getSession());
		this.model = model;
	}
	
	public String getEmail() {
		return this.request.getParameter(EMAIL_TAG);
	}
	public String getPassword() {
		return this.request.getParameter(PASSWORD_TAG);
	}
	
	public void modelAddEmail() {
		this.model.addAttribute(EMAIL_TAG, this.getEmail());
	}
	
	public void sessionAddEmail() {
		this.session.addAttribute(EMAIL_TAG,this.getEmail());
	}
	
	public void responseSetStatusOk() {
		this.response.setStatusOk();	
	}
	
	public void responseSetStatusLoginError() {
		this.response.setStatusLoginError();
	}
	
	public void sessionInvalidate() {
			this.session.invalidate();
		}
	
}
