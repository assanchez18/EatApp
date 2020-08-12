package es.restaurant.EatApp.generalControllers;

import java.util.HashMap;
import java.util.Map;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.OrderState;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.models.ProductState;
import es.restaurant.EatApp.repositories.ProductDao;
import es.restaurant.EatApp.views.OrderView;

public abstract class OrderController {

	protected Order order;

	protected String createOrder() {
		OrderView view = this.getView();
		Integer[] ids = view.getIds();
		Integer[] amounts = view.getAmounts();
		String parameters = view.getParameter();
		if(ids.length == 0 || amounts.length == 0) {
			return view.errorWithOrder();
		}
		this.order = createOrder(ids, amounts, parameters);
		if(order.getProducts().size() == 0) {
			return view.errorEmptyOrder();
		}
		return interact();
	}

	private Order createOrder(Integer[] ids, Integer[] amounts, String parameters) {
		Map<Product, Integer> products =  new HashMap<>();
		ProductDao productDao = ProductDao.getProductDao();
		for (int i = 0; i < ids.length; i++) {
			Product product = productDao.getProductById(ids[i]);
			if(amounts[i] == 0 || product == null) {
				continue;
			}
			products.put(product, amounts[i]);
		}
		return new Order(products, parameters, new OrderState());
	}

	protected void initializeProductStates(ProductState state) {
		for(Product product : this.order.getProducts().keySet()) {
			product.setState(state);
		}
	}

	protected abstract String interact();

	protected abstract OrderView getView();
}
