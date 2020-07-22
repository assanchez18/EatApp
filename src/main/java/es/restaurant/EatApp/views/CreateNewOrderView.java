package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class CreateNewOrderView extends View {
	public CreateNewOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String redirect() {
		this.response.setStatusOk();
		return "createNewOrder";
	}
}