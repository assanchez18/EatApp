package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import es.restaurant.EatApp.models.UserType.userType;

public class RegisterNewUserView extends RegisterView {

	private final String MSG_TITLE = "Introduce tus datos";

	public RegisterNewUserView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	@Override
	protected void updateModelMsg() {
		this.model.addAttribute(TAG_TITLE, MSG_TITLE);
	}

	@Override
	public int getUserType() {
		return userType.COMMENSAL.ordinal();
	}
	
}
