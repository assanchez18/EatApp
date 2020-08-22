package es.restaurant.EatApp.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.views.helpers.TableHelperView;

public class CreateNewOrderView extends OrderView {

	private final String VIEW_CREATE_NEW_ORDER = "createNewOrder";

	private TableHelperView tableHelper;
	
	public CreateNewOrderView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.tableHelper = new TableHelperView(this.request);
	}
	
	public String shorOrderView(Order order) {
		this.updateSession(order);
		return this.showOrderView();
	}
	
	public String createNewOrderForm(Order order) {
		setStatusOk();
		this.prepareModel(order);
		return VIEW_CREATE_NEW_ORDER;
	}

	public String redirectToRegistryInTable() {
		this.response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
		return RegisterInTableView.VIEW_REGISTRY_IN_TABLE;
	}

	public boolean hasTableCode() {
		return !(this.tableHelper.getSessionTableCode() == null);
	}

	public int getTableCode() {
		return this.tableHelper.getSessionTableCode();
	}

	public boolean isOrderInProccess() {
		return this.getOrder() != null;
	}

	public boolean isOrderOpen() {
		return this.getOrder().isOpen();
	}

}