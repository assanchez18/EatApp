package es.restaurant.EatApp.generalControllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.ProductDao;
import es.restaurant.EatApp.views.ShowOrderView;

@Controller
public class showOrderController {

	@PostMapping("/showNewOrder")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res, @RequestParam(value="ids[]") Integer[] ids, @RequestParam(value="amounts[]") Integer[] amounts) {
		ShowOrderView view = new ShowOrderView(model, req, res);
		System.out.println("es show order");
		if(ids.length > 0 && amounts.length > 0) {
			Order order = createTemporalOrder(ids,amounts);
			return view.interact(order);
		}
		return view.interact();
	}
	
	private Order createTemporalOrder(Integer[] ids, Integer[] amounts) {
		Map<Product, Integer> products =  new HashMap<>();
		ProductDao productDao = ProductDao.getProductDao();
		for (int i = 0; i < ids.length; i++) {
			Product product = productDao.getProductById(ids[i]);
			products.put(product, amounts[i]);
		}
		Order order = new Order();
		order.setProducts(products); // TODO Save order in db and session
		return order;
	}

}
