package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;


public class AskForHelpView extends View {

	
	public AskForHelpView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String askForHelp() {
		//stuff
		return "mainUserView";
	}

	public String error() {
		//stuff
		return "index";
	}

}
