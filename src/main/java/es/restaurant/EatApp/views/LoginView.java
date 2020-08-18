package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.views.helpers.TableHelperView;

public class LoginView extends View {

	public static final String TAG_PASSWORD = "password";
	private static final String TAG_ORDER = "order";
	private static final String TAG_TYPE = "type";
	private static final String MSG_LOGIN_ERROR = "El usuario o la contraseña son incorrectos";
	
	private TableHelperView tableHelperView;
	
	public LoginView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.tableHelperView = new TableHelperView(this.request, this.session);
	}

	public String login(User user) {
		this.session.setAttribute(TAG_EMAIL, this.getEmail());
		this.session.setAttribute(TAG_TYPE, user.getUserType().getTypeName());
		setStatusOk();
		return VIEW_MAIN_USER;
	}

	public String errorUnauthorized() {
		return this.returnErrorWithMessage(MSG_LOGIN_ERROR, HttpServletResponse.SC_UNAUTHORIZED, VIEW_ERROR);
	}

	public String getPassword() {
		return this.request.getParameter(TAG_PASSWORD);
	}

	public void recuperateOrder(Order order) {
		this.session.setAttribute(TAG_ORDER, order);
	}

	@Override
	public String getEmail() {
		return this.request.getParameter(TAG_EMAIL);
	}

	public void setTable(int code) {
		this.tableHelperView.setTable(code);
	}
}
