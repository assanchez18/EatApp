package es.restaurant.EatApp.views.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EmailHelperView {

	public static final String TAG_EMAIL = "email";
	
	private HttpServletRequest request;
	private HttpSession session;
	
	public EmailHelperView(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession();
	}

	public String getEmail() {
		return (String) this.session.getAttribute(TAG_EMAIL);
	}

	public void setEmail() {
		this.session.setAttribute(TAG_EMAIL, this.getRequestedEmail());
	}

	public String getRequestedEmail() {
		return this.request.getParameter(TAG_EMAIL);
	}
}
