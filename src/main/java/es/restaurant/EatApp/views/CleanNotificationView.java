package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class CleanNotificationView extends ShowNotificationView {
	
	private static final String NOTIFICATION_ID = "notificationId";
	
	public CleanNotificationView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public int getNotificationId() {
		return Integer.decode(this.request.getParameter(NOTIFICATION_ID));
	}
}
