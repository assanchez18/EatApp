package es.restaurant.EatApp.generalControllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Notification;
import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.OrderState;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.models.Employee;
import es.restaurant.EatApp.repositories.CookDao;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.repositories.ProductDao;
import es.restaurant.EatApp.repositories.WaiterDao;
import es.restaurant.EatApp.views.CreateNewOrderView;

@Controller
public class CreateNewOrderController extends OrderController {

	private CreateNewOrderView view;

	@GetMapping("/createOrder")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new CreateNewOrderView(model, req, res);
		boolean orderInProcess = this.view.isOrderInProgress();
		Order baseOrder = createEmptyOrder();
		if(orderInProcess) {
			mergeOrders(baseOrder, this.view.getOrder());
		}
		return view.interact(baseOrder);
	}

	@PostMapping("/createOrder")
	public String controlPost(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view= new CreateNewOrderView(model, req, res);
		return createOrder();
	}

	protected String interact() {
		OrderDao.getOrderDao().saveInCache(this.order);
		makeEmployersObserveOrder();
		this.order.changeStatus(Notification.Type.ORDER_QUEUED, view.getTableCode());
		this.view.updateSession(order);
		return this.view.interact(this.order);
	}

	private void makeEmployersObserveOrder() {
		for (Employee w : WaiterDao.getWaiterDao().getWaiters()) {
			w.addObserver(this.order);
		}
		for (Employee c : CookDao.getCookDao().getCooks()) {
			c.addObserver(this.order);
		}
	}

	protected CreateNewOrderView getView() {
		return this.view;
	}

	private Order createEmptyOrder() {
		Map<Product, Integer> products =  new HashMap<Product, Integer>();
		ProductDao productDao = ProductDao.getProductDao();
		for(Product product : productDao.getProducts()) {
			products.put(product, 0);
		}
		return new Order(products, "", new OrderState(OrderState.orderState.QUEUED));
	}

	private void mergeOrders(Order baseOrder, Order otherOrder) {
		for(Entry<Product, Integer> product : otherOrder.getProducts().entrySet()) {
			for(Entry<Product, Integer>baseProduct : baseOrder.getProducts().entrySet()) {
				if(baseProduct.getKey().equals(product.getKey())) {
					baseProduct.setValue(product.getValue()); 
				} 
			} 
		} 
		baseOrder.setParameters(otherOrder.getParameters());
	}
}
