package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.restaurant.EatApp.views.helpers.EmailHelperView;

public class StartView extends View {

	private EmailHelperView emailHelper;

	public StartView(HttpServletRequest req,  HttpServletResponse res) {
		super(req, res);
		this.emailHelper = new EmailHelperView(req);
	}
	
	public String interact() {
		setStatusOk();
		return this.emailHelper.getEmail() != null ? mainView() : start();
	}
	
	private String start() {
		return VIEW_INDEX;
	}
	
	private String mainView() {
		return VIEW_MAIN_USER;
	}
}
