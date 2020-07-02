package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartView extends View {

	public StartView(HttpServletRequest req,  HttpServletResponse res) {
		super(req, res);
	}
	
	public String interact() {
		this.response.setStatusOk();
		return getEmail() != null ? mainView() : start();
	}
	
	private String start() {
		return "index";
	}
	
	private String mainView() {
		return "mainUserView";
	}
}
