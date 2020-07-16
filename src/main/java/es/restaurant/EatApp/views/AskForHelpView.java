package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class AskForHelpView extends View {

	private static final String TABLE_TAG = "table";
	private static final String helpMessage = "Gracias. Un camarero viene a ayudarte!";
	
	public AskForHelpView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interact() {
		this.model.addAttributeHelp(helpMessage);
		this.response.setStatusOk();
		return "mainUserView";
	}
	
	public int getTableCode() {
		return this.session.getIntAttribute(TABLE_TAG);
	}
}
