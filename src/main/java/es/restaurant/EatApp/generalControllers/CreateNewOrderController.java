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
import es.restaurant.EatApp.views.CreateNewOrderView;

@Controller
public class CreateNewOrderController {

	@GetMapping("/createOrder")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		CreateNewOrderView view = new CreateNewOrderView(model, req, res);
		return view.interactGet(ProductDao.getProductDao().getProducts());
	}

	@PostMapping("/createOrder")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res) {
		CreateNewOrderView view = new CreateNewOrderView(model, req, res);
		Integer[] ids = view.getParameterArray(CreateNewOrderView.IDS_TAG);
		Integer[] amounts = view.getParameterArray(CreateNewOrderView.AMOUNTS_TAG);
		String parameters = view.getParameter(CreateNewOrderView.PARAMS_TAG);
		if(ids.length == 0 || amounts.length == 0) {
			return view.errorBadRequest(CreateNewOrderView.ERROR);
		}
		Order order = createOrder(ids,amounts, parameters);
		if(order.getProducts().size() == 0) {
			return view.errorBadRequest(CreateNewOrderView.ERROR_EMPTY);
		}
		return view.interactPost(order);
	}
	
	private Order createOrder(Integer[] ids, Integer[] amounts, String parameters) {
		Map<Product, Integer> products =  new HashMap<>();
		ProductDao productDao = ProductDao.getProductDao();
		for (int i = 0; i < ids.length; i++) {
			Product product = productDao.getProductById(ids[i]);
			// TODO Handle error in ids -> maybe we can only obviate that product
			if(amounts[i] == 0) {
				continue;
			}
			products.put(product, amounts[i]);
		}
		Order order = new Order();
		order.setProducts(products); // TODO Save order in db
		order.setState(new OrderState(OrderState.orderState.QUEUED));
		order.setParameters(parameters);
		return order;
	}

}
