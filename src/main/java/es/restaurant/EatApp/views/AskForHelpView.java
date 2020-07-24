package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class AskForHelpView extends View {

	private static final String HELP_TAG = "help";
	private static final String HELP_MESSAGE = "Gracias. Un camarero viene a ayudarte!";
	
	public AskForHelpView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interact() {
		this.model.addAttribute(HELP_TAG, HELP_MESSAGE);
		setStatusOk();
		return "mainUserView";
	}
	
	public int getTableCode() {
		return (int) this.session.getAttribute(TABLE_TAG);
	}
}
