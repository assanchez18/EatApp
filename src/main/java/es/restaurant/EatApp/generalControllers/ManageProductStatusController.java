package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.views.ManageProductStatusView;

@Controller
public class ManageProductStatusController {

	private ManageProductStatusView view;
	private static final String CANCEL_TAG = "cancel";

	@GetMapping("/manageProductStatus")
	public String prepareOrdersManageProductStatus(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new ManageProductStatusView(model, req, res);
		return this.view.interact(OrderDao.getOrderDao().getOrdersFromCache());
	}
	
	@PostMapping("/manageProductStatus")
	public String processingManageProductStatus(Model model, HttpServletRequest req, HttpServletResponse res) {
		System.out.println("post");
		this.view = new ManageProductStatusView(model, req, res);
		int userId = this.view.getUserId();
		int productId = this.view.getProductId();
		Order order = OrderDao.getOrderDao().takeFromCacheWithUserId(userId);
		for(Product product : order.getProducts().keySet()) {
			if(product.getId() == productId) {
				if(this.view.getOperation().equals(CANCEL_TAG)) {
					product.cancel();
				} else {
					product.setNextState();
				}
			}
		}
		order.calculateNextState();
		// TODO filter orders to show depending on the employee type
		return this.view.interact(OrderDao.getOrderDao().getOrdersFromCache());
	}

}
