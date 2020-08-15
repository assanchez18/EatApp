package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class CleanNotificationView extends ShowNotificationView {
	
	public static final String TAG_NOTIFICATION_ID = "notificationId";
	private static final String MSG_ERROR_NO_EMPLOYEE = "El empleado no existe";
	
	public CleanNotificationView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public int getNotificationId() {
		return Integer.decode(this.request.getParameter(TAG_NOTIFICATION_ID));
	}

	public String errorNoEmployee() {
		return returnErrorWithMessage(MSG_ERROR_NO_EMPLOYEE, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}
}
