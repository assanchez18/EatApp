package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.UserSql;

public class LogoutView extends View {
		
	public LogoutView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}
	public LogoutView(HttpServletRequest req) {
		super(req);
	}

	public String logout(UserSql user) {
		this.session.invalidate();		
		return "/index";
	}
	
	public String getEmail() {
		return this.session.getAttribute(EMAIL_TAG);
	}

}
