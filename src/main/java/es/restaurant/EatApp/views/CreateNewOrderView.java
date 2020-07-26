package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.Product;

public class CreateNewOrderView extends View {
	
	public static final String ORDER_TAG = "order";
	public static final String IDS_TAG = "ids[]";
	public static final String AMOUNTS_TAG = "amounts[]";
	public static final String PARAMS_TAG = "parameters";
	public static final String ERROR_EMPTY = "Vaya!, parece que tu pedido estaba vacío";
	public static final String ERROR = "Ha habido un problema con tu pedido, vuelve a intentarlo";

	public CreateNewOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}
	
	public String interactGet(Collection<Product> collection) {
		this.model.addAttribute("products", collection);
		setStatusOk();
		return "createNewOrder";
	}
	
	public String interactPost(Order order) {
		this.session.removeAttribute(ORDER_TAG);
		this.model.addAttribute(ORDER_TAG, order);
		setStatusOk();
		return "showOrder";
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
	
	public String getParameter(String parameterName) {
		return this.request.getParameter(parameterName);
	}
	
	public String errorBadRequest(String message) {
		return this.returnErrorWithMessage(message, HttpServletResponse.SC_BAD_REQUEST, ERROR_TAG);
	}
	
}