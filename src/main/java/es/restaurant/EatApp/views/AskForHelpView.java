package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.views.helpers.TableHelperView;

public class AskForHelpView extends View {

	private final String TAG_HELP = "help";
	private final String MSG_HELP = "Gracias. Un camarero viene a ayudarte!";
	private final String MSG_ERROR = "El código de la mesa no existe. No se ha podido solicitar ayuda.";
	
	private TableHelperView tableHelper;
	
	public AskForHelpView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.tableHelper = new TableHelperView(this.request);
	}
	
	public String askForHelp() {
		this.model.addAttribute(TAG_HELP, MSG_HELP);
		setStatusOk();
		return VIEW_MAIN_USER;
	}

	public String wrongTableCode() {
		return returnErrorWithMessageAndErrorCode(MSG_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}

	public int getTableCode() {
		return this.tableHelper.getSessionTableCode();
	}
}
