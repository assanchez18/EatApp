package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.views.helpers.EmailHelperView;

public class LogoutView extends View {
	
	private EmailHelperView emailHelper;
	
	public LogoutView(HttpServletRequest req) {
		super(req);
		this.emailHelper = new EmailHelperView(req);
	}

	public String logout(User user) {
		this.session.invalidate();		
		return VIEW_INDEX;
	}

	public String getEmail() {
		return this.emailHelper.getEmail();
	}
}
