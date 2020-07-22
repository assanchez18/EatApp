package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

public class RegisterInTableView extends View {
	
	private static final String CODE_TAG = "code";
	private static final String CODE_ERROR_MSG = "El código escaneado es incorrecto";
	
	public RegisterInTableView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String register() {
		this.session.setAttribute(TABLE_TAG, this.getCode());
		setStatusOk();
		return "mainUserView";
	}

	public String error() {
		addError(CODE_ERROR_MSG);
		setStatusNotFoundError();
		return this.redirect();
	}
	
	public int getCode() {
		return Integer.parseInt(this.request.getParameter(CODE_TAG));
	}

	public String redirect() {
		return "registryInTable";
	}

}
