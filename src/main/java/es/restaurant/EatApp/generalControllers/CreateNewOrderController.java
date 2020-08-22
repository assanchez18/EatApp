
package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.repositories.EmployeeDao;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.views.CreateNewOrderView;

@Controller
public class CreateNewOrderController extends OrderController {

	private CreateNewOrderView view;

	@GetMapping("/createOrder")
	public String prepareCreateOrder(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new CreateNewOrderView(model, req, res);
		if(!this.view.hasTableCode()) {
			return this.view.redirectToRegistryInTable();
		}
		Order baseOrder = new Order();
		if (!this.view.isOrderInProccess()) {
			return this.view.createNewOrderForm(baseOrder);
		}
		if (this.view.isOrderOpen()) {
			baseOrder.mergeOrder(this.view.getOrder());
			return this.view.createNewOrderForm(baseOrder);
		}
		if (this.view.getOrder() != null ) {
			return this.view.shorOrder(this.view.getOrder());
		}
		return view.shorOrder(baseOrder);
	}

	@PostMapping("/createOrder")
	public String createOrder(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view= new CreateNewOrderView(model, req, res);
		return createOrder();
	}

	protected String showOrder() {
		this.order.changeStatusToQueued(view.getTableCode());
		this.order.initializeProductsAsQueued();
		this.order.calculateTotalPrice();
		OrderDao.getOrderDao().saveInCache(this.order);
		makeEmployeesObserveOrder();
		return this.view.shorOrder(this.order);
	}

	private void makeEmployeesObserveOrder() {
		EmployeeDao.getEmployeeDao().getAllEmployeesFromCache().forEach(e -> e.addObserver(this.order));
	}

	protected CreateNewOrderView getView() {
		return this.view;
	}
}
