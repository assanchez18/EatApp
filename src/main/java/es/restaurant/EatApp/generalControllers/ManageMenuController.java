package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.repositories.ProductDao;
import es.restaurant.EatApp.views.ManageMenuView;

@Controller
public class ManageMenuController {

	@GetMapping("/manageMenu")
	public String showManageMenuOptions(Model model, HttpServletRequest req, HttpServletResponse res) {
		ManageMenuView view = new ManageMenuView(model, res);
		return view.showMenuWithOptions(ProductDao.getProductDao().getProducts());
	}
}
