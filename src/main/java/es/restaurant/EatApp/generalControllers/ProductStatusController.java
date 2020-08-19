package es.restaurant.EatApp.generalControllers;

import es.restaurant.EatApp.models.Notification;
import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.repositories.TableDao;
import es.restaurant.EatApp.views.ProductView;

public abstract class ProductStatusController {

	protected String handleStates() {
		Order order = OrderDao.getOrderDao().takeFromCacheWithUserId(this.getView().getUserId());
		if(order.isValid()) {
			for(Product product : order.getProducts().keySet()) {
				if(product.getId() == this.getView().getProductId()) {
					this.changeState(product);
					order.updateState();
					this.processNotification(order, product);
					return this.interact();
				}
			}
		}
		return this.getView().error();
	}

	private void processNotification(Order order, Product product) { 
		if(product.isCancelled() || product.isReady() || product.isQueued()) {
			order.changeStatus(new Notification(product, TableDao.getTableDao().getTableWithUserId(order.getUserId())));
		}
	}

	protected abstract String interact();

	protected abstract void changeState(Product product);
	
	protected abstract ProductView getView();
}
