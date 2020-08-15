package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class RegisterEmployeeView extends RegisterView {

	public static final String TAG_USER_TYPE = "userType";
	private static final String MSG_TITLE = "Registrar nuevo empleado";
	
	public RegisterEmployeeView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}


	@Override
	protected void updateModel() {
		this.model.addAttribute(TAG_TITLE, MSG_TITLE);
	}

	@Override
	public int getUserType() {
		return Integer.parseInt(this.request.getParameter(TAG_USER_TYPE));
	}
}
