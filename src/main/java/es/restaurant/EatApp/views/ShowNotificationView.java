package es.restaurant.EatApp.views;

import java.util.List;

import es.restaurant.EatApp.models.Notification;
import es.restaurant.EatApp.views.helpers.EmailHelperView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class ShowNotificationView extends View {

	private final String TAG_NOTIFICATIONS = "notifications";
	private EmailHelperView emailHelper;


	public ShowNotificationView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
		this.emailHelper = new EmailHelperView(req);
	}

	public String interact(List<Notification> notifications) {
		this.model.addAttribute(TAG_NOTIFICATIONS, notifications);
		setStatusOk();
		return VIEW_MAIN_USER;
	}
	public String getEmail() {
		return this.emailHelper.getEmail();
	}
	
}
