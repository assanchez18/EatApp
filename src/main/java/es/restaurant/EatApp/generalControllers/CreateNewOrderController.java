package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.repositories.ProductDao;
import es.restaurant.EatApp.views.CreateNewOrderView;

@Controller
public class CreateNewOrderController extends OrderController {

	private CreateNewOrderView view;
	private Order order;
	
	@GetMapping("/createOrder")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new CreateNewOrderView(model, req, res);
		return view.interactGet(ProductDao.getProductDao().getProducts());
	}

	@PostMapping("/createOrder")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view= new CreateNewOrderView(model, req, res);
		this.order = new Order();
		return createOrder(view, order);
	}

	protected String interact() {
		return this.view.interactPost(this.order);
	}
}
