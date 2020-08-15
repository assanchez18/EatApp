package es.restaurant.EatApp.generalControllers;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.ProductView;

public abstract class ProductStatusController {
	
	ProductView view;

	protected String handleStates() {
		int userId = this.view.getUserId();
		int productId = this.view.getProductId();
		Order order = OrderDao.getOrderDao().takeFromCacheWithUserId(userId);
		if(order != null) {
			for(Product product : order.getProducts().keySet()) {
				if(product.getId() == productId) {
					this.doAction(product);
					order.calculateNextState();
					return this.view.interact(OrderDao.getOrderDao().getOrdersFromCache(), isCommensal());
				}
			}
		}
		return this.view.error();
	}
	
	private boolean isCommensal() {
		return UserDao.getUserDao().getUser(this.view.getEmail()).isCommensal();
	}

	protected abstract void doAction(Product product);
	
}
