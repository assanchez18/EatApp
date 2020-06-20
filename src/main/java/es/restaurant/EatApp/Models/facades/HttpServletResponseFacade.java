package es.restaurant.EatApp.Models.facades;

import javax.servlet.http.HttpServletResponse;

public class HttpServletResponseFacade {

	private HttpServletResponse response;
	
	public HttpServletResponseFacade(HttpServletResponse res) {
		this.response = res;
	}
	
	public void setStatusOk() {
		this.response.setStatus(HttpServletResponse.SC_OK);
	}
	
	public void setStatusLoginError() {
		this.response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
