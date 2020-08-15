package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class AskForHelpView extends View {

	private static final String TAG_HELP = "help";
	private static final String MSG_HELP = "Gracias. Un camarero viene a ayudarte!";
	private static final String MSG_ERROR = "El código de la mesa no existe. No se ha podido solicitar ayuda.";
	
	public AskForHelpView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interact() {
		this.model.addAttribute(TAG_HELP, MSG_HELP);
		setStatusOk();
		return VIEW_MAIN_USER;
	}

	public String wrongTableCode() {
		return returnErrorWithMessage(MSG_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}
}
