package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.views.helpers.EmailHelperView;
import es.restaurant.EatApp.views.helpers.OrderHelperView;
import es.restaurant.EatApp.views.helpers.TableHelperView;

public class LoginView extends View {

	public static final String TAG_PASSWORD = "password";
	private static final String TAG_TYPE = "type";
	private static final String MSG_LOGIN_ERROR = "El usuario o la contraseña son incorrectos";
	
	private TableHelperView tableHelper;
	private EmailHelperView emailHelper;
	private OrderHelperView orderHelper;

	public LoginView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.tableHelper = new TableHelperView(req);
		this.emailHelper = new EmailHelperView(req);
		this.orderHelper = new OrderHelperView(model, req.getSession());
	}

	public String login(User user) {
		this.emailHelper.setEmail();
		this.session.setAttribute(TAG_TYPE, user.getUserType().getTypeName());
		setStatusOk();
		return VIEW_MAIN_USER;
	}

	public String errorUnauthorized() {
		return this.returnErrorWithMessageAndErrorCode(MSG_LOGIN_ERROR, HttpServletResponse.SC_UNAUTHORIZED);
	}

	public String getEmail() {
		return this.emailHelper.getRequestedEmail();
	}
	
	public String getPassword() {
		return this.request.getParameter(TAG_PASSWORD);
	}

	public void setOrder(Order order) {
		this.orderHelper.updateOrder(order);
	}

	public void setTable(int code) {
		this.tableHelper.setTable(code);
	}
}
