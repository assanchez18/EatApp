package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;


public class ShowOrderView extends OrderView {
	
	public ShowOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public String interactPost() {
		setStatusOk();
		return SHOW_ORDER_VIEW;
	}
}