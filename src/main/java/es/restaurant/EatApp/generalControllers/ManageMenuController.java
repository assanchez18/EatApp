package es.restaurant.EatApp.generalControllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.ProductDao;
import es.restaurant.EatApp.repositories.ProductIngredientsDao;
import es.restaurant.EatApp.views.ManageMenuView;

@Controller
public class ManageMenuController {

	@GetMapping("/manageMenu")
	public String showManageMenuOptions(Model model, HttpServletRequest req, HttpServletResponse res) {
		ManageMenuView view = new ManageMenuView(model, res);
		return view.showMenuWithOptions(ProductDao.getProductDao().getProducts());
	}

	@PostMapping("/changeProductInMenu")
	public String showChangeMenuProduct(Model model, HttpServletRequest req, HttpServletResponse res) {
		ManageMenuView view = new ManageMenuView(model, req, res);
		return view.showProductForm(ProductDao.getProductDao().getProductById(view.getProductId()));
	}
	
	@PostMapping("/productChanged")
	public String changeProduct(Model model, HttpServletRequest req, HttpServletResponse res) {
		ManageMenuView view = new ManageMenuView(model, req, res);
		if(view.getProductName().isEmpty()) {
			return view.errorNoProductName();
		}
		if(view.getProductDescription().isEmpty()) {
			return view.errorNoProductDescription();
		}
		List<Ingredient> productIngredients = ProductIngredientsDao.getProductIngredientDao().getIngredientsOfProduct(view.getProductId());
		if (productIngredients == null) {
			return view.errorNoIngredients();
		}
		Product product = new Product(view.getProductId(), view.getProductName(), view.getProductDescription(), view.getProductPrice(), view.getProductPriority());
		if(!ProductDao.getProductDao().updateProduct(product)) {
			return view.errorInDB();
		}
		List<Ingredient> noProductIngredients = ProductIngredientsDao.getProductIngredientDao().getAllIngredientsunlessOfProduct(view.getProductId());
		return view.showIngredientsForm(product, productIngredients, noProductIngredients);
	}
	
	@PostMapping("/newProductIngredients")
	public String updateProductIngredients(Model model, HttpServletRequest req, HttpServletResponse res) {
		ManageMenuView view = new ManageMenuView(model, req, res);
		List<Integer> ingredientsInProduct = getNewIngredientsList(view.getIds(), view.areIngredientIncluded());
		if (ingredientsInProduct == null) {
			return view.errorNoIngredients();
		}
		if(!ProductIngredientsDao.getProductIngredientDao().updateIngredientsInProduct(ingredientsInProduct, view.getProductId())) {
			view.errorInDB();
		}
		return view.interact();
	}
	
	private List<Integer> getNewIngredientsList(Integer[] ingredientsId, List<Boolean> areContained) {
		List<Integer> newIngredients = new ArrayList<Integer>();
		if(ingredientsId == null || areContained == null) {
			return null;
		}
		for (int i = 0; i < areContained.size(); i++) {
			if(areContained.get(i)) {
				newIngredients.add(ingredientsId[i]);
			}
		}
		return newIngredients.size() == 0 ? null : newIngredients;
	}

	@PostMapping("/removeProductInMenu")
	public String removeMenuProduct(Model model, HttpServletRequest req, HttpServletResponse res) {
		ManageMenuView view = new ManageMenuView(model, req, res);
		if(ProductDao.getProductDao().deleteProduct(view.getProductId())) {
			view.errorInDB();
		}
		return view.interact();
	}
	
	@PostMapping("/addProductInMenu")
	public String newMenuProduct(Model model, HttpServletRequest req, HttpServletResponse res) {
		ManageMenuView view = new ManageMenuView(model, res);
		if(view.getProductName().isEmpty()) {
			return view.errorNoProductName();
		}
		if(view.getProductDescription().isEmpty()) {
			return view.errorNoProductDescription();
		}
		Product product = new Product(view.getProductName(), view.getProductDescription(), view.getProductPrice(), view.getProductPriority());
		if(!ProductDao.getProductDao().insert(product)) {
			return view.errorInDB();
		}
		return view.showProductForm(new Product());
	}

}