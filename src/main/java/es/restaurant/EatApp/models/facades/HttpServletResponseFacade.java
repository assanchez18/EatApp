package es.restaurant.EatApp.models.facades;

import javax.servlet.http.HttpServletResponse;

public class HttpServletResponseFacade {

	private HttpServletResponse response;
	
	public HttpServletResponseFacade(HttpServletResponse res) {
		this.response = res;
	}
	
	public void setStatusOk() {
		this.response.setStatus(HttpServletResponse.SC_OK);
	}
	
	public void setStatusUnauthorizedLoginError() {
		this.response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}

	public void setStatusNotFoundLoginError() {
		this.response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
}