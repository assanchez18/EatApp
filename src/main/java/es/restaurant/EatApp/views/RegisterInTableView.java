package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import es.restaurant.EatApp.models.UserSql;

public class RegisterInTableView extends View {
	
	private static final String CODE_TAG = "code";
	private static final String TABLE_TAG = "table";
	private static final String CODE_ERROR = "El código escaneado es incorrecto";
	
	public RegisterInTableView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String register() {
		this.session.addAttribute(TABLE_TAG, this.getCode());
		this.response.setStatusOk();
		return "mainUserView";
	}

	public String error() {
		this.model.addAttributeError(CODE_ERROR);
		this.response.setStatusNotFoundError();
		return "registryInTable";
	}
	
	public boolean decode() {
		int code = Integer.parseInt(this.getCode());
		if(code > 50000) {
			return false;
		}
		// TODO check codes and Create tables codes in DB
		return true;
	}
	
	private String getCode() {
		return this.request.getParameter(CODE_TAG);
	}

}
