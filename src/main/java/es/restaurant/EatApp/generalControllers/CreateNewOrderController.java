package es.restaurant.EatApp.generalControllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res, @RequestParam(value="ids[]") Integer[] ids, @RequestParam(value="amounts[]") Integer[] amounts) {
		Order order = createOrder(ids,amounts);
		System.out.println("es create order");
		CreateNewOrderView view = new CreateNewOrderView(model, req, res);
		return view.interactPost(order);
	}
	
	private Order createOrder(Integer[] ids, Integer[] amounts) {
		Map<Product, Integer> products =  new HashMap<>();
		ProductDao productDao = ProductDao.getProductDao();
		for (int i = 0; i < ids.length; i++) {
			Product product = productDao.getProductById(ids[i]);
			products.put(product, amounts[i]);
		}
		Order order = new Order();
		order.setProducts(products); // TODO Save order in db
		order.setState(new OrderState(OrderState.orderState.QUEUED));
		return order;
	}

}
