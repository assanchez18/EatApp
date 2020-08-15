package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

public class RegisterInTableView extends View {
	
	public static final String TAG_CODE = "code";
	private static final String MSG_CODE_ERROR = "El código escaneado es incorrecto";
	public static final String VIEW_REGISTRY_IN_TABLE = "registryInTable";
	
	public RegisterInTableView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String register() {
		this.session.setAttribute(TAG_TABLE, this.getTableCode());
		setStatusOk();
		return VIEW_MAIN_USER;
	}
	
	public String errorNotFound() {
		return this.returnErrorWithMessage(MSG_CODE_ERROR, HttpServletResponse.SC_NOT_FOUND, VIEW_REGISTRY_IN_TABLE);
	}
	
	@Override
	public Integer getTableCode() {
		return Integer.valueOf(this.request.getParameter(TAG_CODE));
	}

	public String interact() {
		return VIEW_REGISTRY_IN_TABLE;
	}

}
