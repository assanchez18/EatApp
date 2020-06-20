package es.restaurant.EatApp.Models.facades;

import javax.servlet.http.HttpSession;

public class SessionFacade {

	private HttpSession session;
	private final static int MAX_INACTIVE_TIME = 600;//In seconds
	
	public SessionFacade(HttpSession session) {
		this.session = session;
		this.session.setMaxInactiveInterval(MAX_INACTIVE_TIME);
	}
	
	public void addAttribute(String name, String value) {
		this.session.setAttribute(name, value);
	}
	
	public void invalidate() {
		this.session.invalidate();
	}
	
}
