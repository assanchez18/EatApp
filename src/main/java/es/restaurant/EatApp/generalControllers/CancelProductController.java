package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.views.ProductView;

@Controller
public class CancelProductController extends ProductStatusController {
	
	@PostMapping("/cancelProduct")
	public String processingCancelProductStatus(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new ProductView(model, req, res);
		return this.handleStates();
	}

	protected void doAction(Product product) {
		product.cancel();
	}

}
