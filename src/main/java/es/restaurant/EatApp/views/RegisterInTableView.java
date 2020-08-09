package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

public class RegisterInTableView extends View {
	
	public static final String CODE_TAG = "code";
	private static final String CODE_ERROR_MSG = "El código escaneado es incorrecto";
	private static final String REGISTRY_IN_TABLE_VIEW = "registryInTable";
	
	public RegisterInTableView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String register() {
		this.session.setAttribute(TABLE_TAG, this.getCode());
		setStatusOk();
		return MAIN_USER_VIEW;
	}
	
	public String errorNotFound() {
		return this.returnErrorWithMessage(CODE_ERROR_MSG, HttpServletResponse.SC_NOT_FOUND, REGISTRY_IN_TABLE_VIEW);
	}
	
	public int getCode() {
		return Integer.parseInt(this.request.getParameter(CODE_TAG));
	}

	public String interact() {
		return REGISTRY_IN_TABLE_VIEW;
	}

}
