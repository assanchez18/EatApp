package es.restaurant.EatApp.models.facades;

import java.util.List;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Notification;

public class ModelFacade {

	private Model model;

	public ModelFacade(Model model) {
		this.model = model;
	}

	public void addAttributeError(String errorMsg) {
		this.model.addAttribute("error", errorMsg);
	}

	public void addNotifications(String msg, List<Notification> list) {
		this.model.addAttribute(msg, list);
	}

	public void addAttributeHelp(String message) {
		this.model.addAttribute("help", message);
	}
}
