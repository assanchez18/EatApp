package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Notification;
import es.restaurant.EatApp.views.helpers.EmailHelperView;

public class CleanNotificationView extends View {
	
	public static final String TAG_NOTIFICATION_ID = "notificationId";
	private static final String MSG_ERROR_NO_EMPLOYEE = "El empleado no existe";
	
	private EmailHelperView emailHelper;
	
	public CleanNotificationView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.emailHelper = new EmailHelperView(req);
	}

	public int getNotificationId() {
		return Integer.decode(this.request.getParameter(TAG_NOTIFICATION_ID));
	}

	public String errorNoEmployee() {
		return returnErrorWithMessage(MSG_ERROR_NO_EMPLOYEE, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}

	public String getEmail() {
		return this.emailHelper.getEmail();
	}
	
	public String interact(List<Notification> notifications) {
		this.model.addAttribute("notifications", notifications);
		setStatusOk();
		return VIEW_MAIN_USER;
	}
}
