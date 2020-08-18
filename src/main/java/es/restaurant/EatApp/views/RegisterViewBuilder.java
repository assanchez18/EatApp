package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.views.helpers.EmailHelperView;

public class RegisterViewBuilder {

	public RegisterViewBuilder() {
		
	}
	
	public RegisterView build(Model model, HttpServletRequest req, HttpServletResponse res) {
		EmailHelperView view = new EmailHelperView(req);
		return (view.getEmail() == null) ? new RegisterNewUserView(model, req, res)
										 : new RegisterEmployeeView(model, req, res);
	}
}
