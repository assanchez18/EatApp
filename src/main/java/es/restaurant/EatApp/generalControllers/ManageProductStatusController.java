package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.views.ProductView;

@Controller
public class ManageProductStatusController extends ProductStatusController {

	ProductView view;

	@GetMapping("/manageProductStatus")
	public String prepareOrdersManageProductStatus(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new ProductView(model, req, res);
		return this.view.interact(OrderDao.getOrderDao().getOrdersFromCache());
	}

	@PostMapping("/manageProductStatus")
	public String processingManageProductStatus(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new ProductView(model, req, res);
		return this.handleStates();
	}

	protected void doAction(Product product) {
		product.setNextState();
	}

	@Override
	protected ProductView getView() {
		return this.view;
	}

	@Override
	protected String interact() {
		return this.view.interact(OrderDao.getOrderDao().getOrdersFromCache());
	}
}
