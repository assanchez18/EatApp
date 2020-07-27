package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class AskForHelpView extends View {

	private static final String HELP_TAG = "help";
	private static final String HELP_MSG = "Gracias. Un camarero viene a ayudarte!";
	private static final String ERROR_MSG = "El código de la mesa no existe. No se ha podido solicitar ayuda.";
	
	public AskForHelpView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interact() {
		this.model.addAttribute(HELP_TAG, HELP_MSG);
		setStatusOk();
		return MAIN_USER_VIEW;
	}
	
	public int getTableCode() {
		return (int) this.session.getAttribute(TABLE_TAG);
	}

	public String wrongTableCode() {
		return returnErrorWithMessage(ERROR_MSG, HttpServletResponse.SC_BAD_REQUEST, ERROR_VIEW);
	}
}
