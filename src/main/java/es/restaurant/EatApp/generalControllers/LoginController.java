package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.Table;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.repositories.TableDao;
import es.restaurant.EatApp.repositories.UserDao;
import es.restaurant.EatApp.views.LoginView;

@Controller
public class LoginController {

	private LoginView view;

	@PostMapping("/login")
	public String login(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new LoginView(model, req, res);
		User user = new User(view.getEmail(), view.getPassword());
		if(!UserDao.getUserDao().isUserCorrect(user)) {
			return this.view.errorUnauthorized();
		}
		user = UserDao.getUserDao().getUser(user.getEmail());
		recuperateInformationFromCache(user.getId());
		return this.view.login(UserDao.getUserDao().getUser(user.getEmail()));
	}

	private void recuperateInformationFromCache(int userId) {
		Table table = TableDao.getTableDao().getTableWithUserId(userId);
		if(table.isValid()) {
			this.view.setTable(table);
			recuperateOrderFromCache(userId); 
		}
	}

	private void recuperateOrderFromCache(int userId) {
		Order order = OrderDao.getOrderDao().takeFromCacheWithUserId(userId);
		if(order.isValid()) {
			this.view.setOrder(order);
		}
	}
}
