package es.restaurant.EatApp.generalControllers;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.OrderView;

public abstract class OrderController {

	protected Order order;

	protected String createOrder() {
		OrderView view = this.getView();
		Integer[] ids = view.getProductIds();
		Integer[] amounts = view.getProductAmounts();
		String parameters = view.getParameter();
		if(ids.length == 0 || amounts.length == 0) {
			return view.errorWithOrder();
		}
		this.order = new Order(ids, amounts, parameters);
		if(!order.hasProducts()) {
			return view.errorEmptyOrder();
		}
		this.order.setUserId(UserDao.getUserDao().getUser(this.getView().getEmail()));
		return showOrder();
	}

	protected abstract String showOrder();

	protected abstract OrderView getView();
}
