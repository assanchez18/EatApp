package es.restaurant.EatApp.models.facades;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpServletRequestFacade {

	private HttpServletRequest request;
	
	public HttpServletRequestFacade(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getParameter(String paramName) {
		return (String) this.request.getParameter(paramName);		
	}

	public HttpSession getSession() {
		return this.request.getSession();
	}
}
