package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.views.helpers.EmailHelperView;
import es.restaurant.EatApp.views.helpers.OrderHelperView;
import es.restaurant.EatApp.views.helpers.ParameterListReader;

public class OrderView extends View {

	public static final String TAG_AMOUNTS = "amounts[]";
	public static final String TAG_PARAMS = "parameters";
	private final String MSG_EMPTY_ORDER_ERROR = "Vaya!, parece que tu pedido estaba vacío";
	private final String MSG_ORDER_ERROR = "Ha habido un problema con tu pedido, vuelve a intentarlo";

	private final String SHOW_ORDER_VIEW = "showOrder";

	private EmailHelperView emailHelper;
	private ParameterListReader parameterListReader;
	private OrderHelperView orderHelper;
	
	public OrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.emailHelper = new EmailHelperView(req);
		this.parameterListReader = new ParameterListReader(req);
		this.orderHelper = new OrderHelperView(model, req.getSession());
	}

	public OrderView(HttpServletResponse res) {
		super(res);
	}

	public OrderView(HttpServletRequest req) {
		super(req);
		this.parameterListReader = new ParameterListReader(req);
		this.emailHelper = new EmailHelperView(req);
		this.orderHelper = new OrderHelperView(req.getSession());
	}

	public String getParameter() {
		return this.request.getParameter(TAG_PARAMS);
	}

	public Integer[] getProductAmounts() {
		return this.parameterListReader.getParameterArray(TAG_AMOUNTS);
	}

	public Integer[] getProductIds() {
		return this.parameterListReader.getIds();
	}

	public String errorWithOrder() {
		return errorBadRequest(MSG_ORDER_ERROR);
	}

	public String errorEmptyOrder() {
		return errorBadRequest(MSG_EMPTY_ORDER_ERROR);
	}

	protected String errorBadRequest(String message) {
		return this.returnErrorWithMessageAndErrorCode(message, HttpServletResponse.SC_BAD_REQUEST);
	}
	
	public Order getOrder() {
		return this.orderHelper.getOrder();
	}
	
	public String getEmail() {
		return this.emailHelper.getEmail();
	}

	public String shorOrderView(Order order) {
		setStatusOk();
		this.prepareModel(order);
		this.updateSession(order);
		return this.SHOW_ORDER_VIEW;
	}

	public String showOrderView() {
		setStatusOk();
		return this.SHOW_ORDER_VIEW;
	}

	public void prepareModel(Order order) {
		this.orderHelper.setOrder(order);
	}

	public void updateSession(Order order) {
		this.orderHelper.updateOrder(order);		
	}
}
