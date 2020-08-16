package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Product;

public class ManageMenuView extends View {

	private static final String TAG_PRODUCTS = "products";
	private static final String VIEW_MANAGE_MENU = "manageMenu";
	
	public ManageMenuView(Model model, HttpServletResponse res) {
		super(model,res);
	}

	public String showMenuWithOptions(Collection<Product> products) {
		this.model.addAttribute(TAG_PRODUCTS, products);
		setStatusOk();
		return VIEW_MANAGE_MENU;
	}

}
