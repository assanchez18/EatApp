package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.CancelProductView;
import es.restaurant.EatApp.views.ProductView;

@Controller
public class CancelProductController extends ProductStatusController {

	CancelProductView view;

	@PostMapping("/cancelProduct")
	public String processingCancelProductStatus(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new CancelProductView(model, req, res);
		return this.handleStates();
	}

	protected void doAction(Product product) {
		product.cancel();
	}

	@Override
	protected ProductView getView() {
		return this.view;
	}

	@Override
	protected String interact() {
		return this.view.interact(OrderDao.getOrderDao().getOrdersFromCache(), isCommensal());
	}

	private boolean isCommensal() {
		return UserDao.getUserDao().getUser(this.view.getEmail()).isCommensal();
	}

}
