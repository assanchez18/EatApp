package es.restaurant.EatApp.models.facades;

import org.springframework.ui.Model;

public class ModelFacade {

	private Model model;
	
	public ModelFacade(Model model) {
		this.model = model;
	}
	
	public void addAttributeError(String errorMsg) {
		this.model.addAttribute("error", errorMsg);
	}
}
