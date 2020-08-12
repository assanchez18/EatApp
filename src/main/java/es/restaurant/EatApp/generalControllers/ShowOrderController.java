package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.OrderState;
import es.restaurant.EatApp.models.ProductState;
import es.restaurant.EatApp.models.ProductState.productState;
import es.restaurant.EatApp.models.OrderState.orderState;
import es.restaurant.EatApp.views.ShowOrderView;

@Controller
public class ShowOrderController extends OrderController {

	private ShowOrderView view;

	@GetMapping("/showNewOrder")
	public String prepareShowOrder(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new ShowOrderView(res);
		return view.interact();
	}

	@PostMapping("/showNewOrder")
	public String showNewOrder(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new ShowOrderView(model, req, res);
		return createOrder();	
	}

	protected String interact() {
		this.order.setState(new OrderState(orderState.OPEN));
		this.initializeProductStates(new ProductState(productState.OPEN));
		return this.view.interact(this.order);
	}

	protected ShowOrderView getView() {
		return this.view;
	}
}
