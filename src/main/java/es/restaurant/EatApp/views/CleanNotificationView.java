package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class CleanNotificationView extends ShowNotificationView {
	
	public static final String NOTIFICATION_ID = "notificationId";
	private static final String ERROR_NO_WAITER_MSG = "El camarero no existe";
	
	public CleanNotificationView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public int getNotificationId() {
		return Integer.decode(this.request.getParameter(NOTIFICATION_ID));
	}

	public String errorNoWaiter() {
		return returnErrorWithMessage(ERROR_NO_WAITER_MSG, HttpServletResponse.SC_BAD_REQUEST, ERROR_VIEW);
	}
}
