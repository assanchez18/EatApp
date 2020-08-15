package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class RegisterViewBuilder {

	public RegisterViewBuilder() {
		
	}
	
	public RegisterView build(Model model, HttpServletRequest req, HttpServletResponse res) {
		View view = new View(model, req, res);
		return (view.getEmail() == null) ? new RegisterNewUserView(model, req, res)
										 : new RegisterEmployeeView(model, req, res);
	}
}
