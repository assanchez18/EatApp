package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.views.ManageProductStatusView;

@Controller
public class ManageProductStatusController {

	private ManageProductStatusView view;

	@GetMapping("/manageProductStatus")
	public String prepareOrdersManageProductStatus(Model model, HttpServletRequest req, HttpServletResponse res) {
		this.view = new ManageProductStatusView(model, req, res);
		return this.view.interact(OrderDao.getOrderDao().getOrdersFromCache());
	}

}
