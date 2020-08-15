package es.restaurant.EatApp.generalControllers;

import es.restaurant.EatApp.models.Notification;
import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.views.ProductView;

public abstract class ProductStatusController {

	protected String handleStates() {
		Order order = OrderDao.getOrderDao().takeFromCacheWithUserId(this.getView().getUserId());
		if(order != null) {
			int productId = this.getView().getProductId();
			for(Product product : order.getProducts().keySet()) {
				if(product.getId() == productId) {
					this.doAction(product);
					order.calculateNextState();
					order.changeStatus(new Notification(product, 123/* TODO table in order - order.getTable()*/));
					return this.interact();
				}
			}
		}
		return this.getView().error();
	}

	protected abstract String interact();

	protected abstract void doAction(Product product);
	
	protected abstract ProductView getView();
}
