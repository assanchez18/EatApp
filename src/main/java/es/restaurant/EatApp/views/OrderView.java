package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;

public abstract class OrderView extends View {

	public static final String ORDER_TAG = "order";
	public static final String IDS_TAG = "ids[]";
	public static final String AMOUNTS_TAG = "amounts[]";
	public static final String PARAMS_TAG = "parameters";
	protected static final String ERROR_EMPTY_ORDER_MSG = "Vaya!, parece que tu pedido estaba vacío";
	protected static final String ERROR_MSG = "Ha habido un problema con tu pedido, vuelve a intentarlo";

	protected static final String SHOW_ORDER_VIEW = "showOrder";

	public OrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public OrderView(HttpServletResponse res) {
		super(res);
	}

	public String getParameter() {
		return this.request.getParameter(PARAMS_TAG);
	}

	public Integer[] getAmounts() {
		return getParameterArray(AMOUNTS_TAG);
	}

	public Integer[] getIds() {
		return getParameterArray(IDS_TAG);
	}

	private Integer[] getParameterArray(String parameterName) {
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
		return errorBadRequest(ERROR_MSG);
	}

	public String errorEmptyOrder() {
		return errorBadRequest(ERROR_EMPTY_ORDER_MSG);
	}

	protected String errorBadRequest(String message) {
		return this.returnErrorWithMessage(message, HttpServletResponse.SC_BAD_REQUEST, ERROR_VIEW);
	}
	
	public Order getOrder() {
		return (Order) this.session.getAttribute(ORDER_TAG);
	}

}
