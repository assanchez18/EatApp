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
					this.processNotification(order, product, 123);
					return this.interact();
				}
			}
		}
		return this.getView().error();
	}

	private void processNotification(Order order, Product product, int i) { // i will be order.getTable()
		if(product.isCancelled() || product.isReady()) {
			order.changeStatus(new Notification(product, i/* TODO table in order - order.getTable()*/));
		}
	}

	protected abstract String interact();

	protected abstract void doAction(Product product);
	
	protected abstract ProductView getView();
}
