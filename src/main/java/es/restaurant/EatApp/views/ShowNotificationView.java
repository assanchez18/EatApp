package es.restaurant.EatApp.views;

import java.util.List;

import es.restaurant.EatApp.models.Notification;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class ShowNotificationView extends View {

	public ShowNotificationView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public String interact(List<Notification> notifications) {
		this.model.addNotifications("notifications", notifications);
		this.response.setStatusOk();
		return "mainUserView";
	}
}
