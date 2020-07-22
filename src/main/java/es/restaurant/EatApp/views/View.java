package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public abstract class View {
	
	protected HttpServletRequest request;
	protected HttpSession session;
	protected HttpServletResponse response;
	protected Model model;

	public static final String TABLE_TAG = "table";
	public static final String EMAIL_TAG = "email";
	private static final String ERROR_TAG = "error";
	
	public View () {
		this.request = null;
		this.response = null;
		this.session = null;
		this.model = null;
	}
	
	public View(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.request = req;
		this.session = req.getSession();
		this.response = res;
		this.model = model;
	}

	public View(HttpServletRequest req) {
		this.request = req;
		this.session = req.getSession();
		this.response = null;
		this.model = null;
	}
	
	public View(HttpServletRequest req,HttpServletResponse res) {
		this.request = req;
		this.session = req.getSession();
		this.response = res;
		this.model = null;
	}

	public String getEmail() {
		return (String) this.session.getAttribute(EMAIL_TAG);
	}
	
	protected void setStatusOk() {
		this.response.setStatus(HttpServletResponse.SC_OK);
	}
	
	protected void setStatusUnauthorizedLoginError() {
		this.response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}

	public void setStatusNotFoundError() {
		this.response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	protected void addError(String msg) {
		this.model.addAttribute(ERROR_TAG, msg);
	}
}
