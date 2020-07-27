package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartView extends View {

	public StartView(HttpServletRequest req,  HttpServletResponse res) {
		super(req, res);
	}
	
	public String interact() {
		setStatusOk();
		return getEmail() != null ? mainView() : start();
	}
	
	private String start() {
		return INDEX_VIEW;
	}
	
	private String mainView() {
		return MAIN_USER_VIEW;
	}
}
