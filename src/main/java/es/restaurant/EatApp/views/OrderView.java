package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.views.helpers.EmailHelperView;

public class OrderView extends View {

	public static final String TAG_ORDER = "order";
	public static final String TAG_IDS = "ids[]";
	public static final String TAG_AMOUNTS = "amounts[]";
	public static final String TAG_PARAMS = "parameters";
	protected static final String MSG_EMPTY_ORDER_ERROR = "Vaya!, parece que tu pedido estaba vacío";
	protected static final String MSG_ORDER_ERROR = "Ha habido un problema con tu pedido, vuelve a intentarlo";

	protected static final String SHOW_ORDER_VIEW = "showOrder";

	private EmailHelperView emailHelper;
	
	public OrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.emailHelper = new EmailHelperView(req);
	}

	public OrderView(HttpServletResponse res) {
		super(res);
	}

	public OrderView(HttpServletRequest req) {
		super(req);
		this.emailHelper = new EmailHelperView(req);
	}

	public String getParameter() {
		return this.request.getParameter(TAG_PARAMS);
	}

	public Integer[] getAmounts() {
		return getParameterArray(TAG_AMOUNTS);
	}

	public Integer[] getIds() {
		return getParameterArray(TAG_IDS);
	}

	public Integer[] getParameterArray(String parameterName) {
		String[] parameters_string = this.request.getParameterValues(parameterName);
		Integer[] parameters = new Integer[parameters_string.length];
		for(int i = 0;i < parameters_string.length;i++)
		{
			try {
				parameters[i] = Integer.parseInt(parameters_string[i]);
			} catch  (NumberFormatException error) {
				return new Integer[0];
			}
		}
		return parameters;
	}

	public String errorWithOrder() {
		return errorBadRequest(MSG_ORDER_ERROR);
	}

	public String errorEmptyOrder() {
		return errorBadRequest(MSG_EMPTY_ORDER_ERROR);
	}

	protected String errorBadRequest(String message) {
		return this.returnErrorWithMessage(message, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}
	
	public Order getOrder() {
		return (Order) this.session.getAttribute(TAG_ORDER);
	}
	
	public String getEmail() {
		return this.emailHelper.getEmail();
	}

}
