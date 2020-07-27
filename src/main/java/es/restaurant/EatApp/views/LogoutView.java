package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;

import es.restaurant.EatApp.models.User;

public class LogoutView extends View {
	
	public LogoutView(HttpServletRequest req) {
		super(req);
	}

	public String logout(User user) {
		this.session.invalidate();		
		return INDEX_VIEW;
	}

}
