package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;

import es.restaurant.EatApp.models.UserSql;

public class LogoutView extends View {

	public LogoutView(HttpServletRequest req) {
		super(req);
	}

	public String logout(UserSql user) {
		this.session.invalidate();		
		return "/index";
	}

}
