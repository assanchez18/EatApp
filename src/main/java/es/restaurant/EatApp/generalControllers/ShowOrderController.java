package es.restaurant.EatApp.generalControllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.OrderState;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.ProductDao;
import es.restaurant.EatApp.views.ShowOrderView;

@Controller
public class ShowOrderController {

	@GetMapping("/showNewOrder")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		ShowOrderView view = new ShowOrderView(model, req, res);
		// TODO get from database, now is failing
		return view.interact();	
	}


	@PostMapping("/showNewOrder")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res) {
		ShowOrderView view = new ShowOrderView(model, req, res);
		Integer[] ids = view.getParameterArray(ShowOrderView.IDS_TAG);
		Integer[] amounts = view.getParameterArray(ShowOrderView.AMOUNTS_TAG);
		String parameters = view.getParameter(ShowOrderView.PARAMS_TAG);
		if(ids.length == 0 || amounts.length == 0) {
			return view.errorBadRequest(ShowOrderView.ERROR);
		}
		Order order = createTemporalOrder(ids,amounts, parameters);
		if(order.getProducts() == null) {
			// TODO Handle error empty order
			return view.errorBadRequest(ShowOrderView.ERROR_EMPTY);
		}
		return view.interact();	
	}

	private Order createTemporalOrder(Integer[] ids, Integer[] amounts, String parameters) {
		Map<Product, Integer> products =  new HashMap<>();
		ProductDao productDao = ProductDao.getProductDao();
		int counter = 0;
		for (int i = 0; i < ids.length; i++) {
			Product product = productDao.getProductById(ids[i]);
			if(amounts[i] == 0) {
				counter++;
			}
			// TODO Handle error in ids -> maybe we can only obviate that product
			products.put(product, amounts[i]);
		}
		Order order = new Order();
		if(counter == amounts.length) {
			return order;
		}
		order.setProducts(products); // TODO Save order in db
		order.setState(new OrderState(OrderState.orderState.QUEUED));
		order.setParameters(parameters);
		return order;
	}

}
