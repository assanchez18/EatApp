package es.restaurant.EatApp.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.models.Product;

public class ManageMenuView extends ProductView {

	private static final String TAG_PRODUCTS = "products";
	private static final String TAG_PRODUCT = "product";
	public static final String TAG_PRODUCT_NAME = "productName";
	public static final String TAG_PRODUCT_DESCRIPTION = "productDescription";
	public static final String TAG_PRODUCT_PRICE = "productPrice";
	public static final String TAG_PRODUCT_PRIORITY = "productPriority";
	private static final String TAG_PRODUCT_INGREDIENTS = "productIngredients";
	private static final String TAG_ALL_INGREDIENTS = "allIngredients";
	public static final String TAG_CONTAINS_INGREDIENTS = "contains[]";

	private static final String MSG_NO_NAME_ERROR ="No has introducido nombre al producto";
	private static final String MSG_NO_DESCRIPTION_ERROR ="No has introducido description al producto";
	private static final String MSG_DB_ERROR ="No se ha podido realizar la acción sobre el producto en la base de datos";
	private static final String MSG_NO_INGREDIENTS_ERROR ="El producto que quieres modificar no tiene ingredientes!";

	private static final String VIEW_MANAGE_MENU = "manageMenuView";
	private static final String VIEW_PRODUCT = "showProduct";
	private static final String VIEW_CHANGE_PRODUCT_INGREDIENTS = "changeProductIngredients";

	public ManageMenuView(Model model, HttpServletResponse res) {
		super(model,res);
	}

	public ManageMenuView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
	}

	public String showMenuWithOptions(Collection<Product> products) {
		this.model.addAttribute(TAG_PRODUCTS, products);
		setStatusOk();
		return VIEW_MANAGE_MENU;
	}

	public String showProductForm(Product product) {
		this.model.addAttribute(TAG_PRODUCT, product);
		setStatusOk();
		return VIEW_PRODUCT;
	}

	public String interact() {
		setStatusOk();
		return VIEW_MAIN_USER;
	}

	public String getProductName() {
		return this.request.getParameter(TAG_PRODUCT_NAME);
	}

	public String getProductDescription() {
		return this.request.getParameter(TAG_PRODUCT_DESCRIPTION);
	}

	public double getProductPrice() {
		return Double.parseDouble(this.request.getParameter(TAG_PRODUCT_PRICE));
	}

	public int getProductPriority() {
		return Integer.parseInt(this.request.getParameter(TAG_PRODUCT_PRIORITY));
	}

	public String errorNoProductName() {
		return returnErrorWithMessage(MSG_NO_NAME_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}

	public String errorNoProductDescription() {
		return returnErrorWithMessage(MSG_NO_DESCRIPTION_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}

	public String errorInDB() {
		return returnErrorWithMessage(MSG_DB_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}

	public String errorNoIngredients() {
		return returnErrorWithMessage(MSG_NO_INGREDIENTS_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}

	public String showIngredientsForm(Product product, List<Ingredient> productIngredients, List<Ingredient> allIngredients) {
		this.model.addAttribute(TAG_PRODUCT_NAME, product.getName());
		this.model.addAttribute(TAG_PRODUCT_ID, product.getId());
		this.model.addAttribute(TAG_PRODUCT_INGREDIENTS, productIngredients);
		this.model.addAttribute(TAG_ALL_INGREDIENTS, allIngredients);
		setStatusOk();
		return VIEW_CHANGE_PRODUCT_INGREDIENTS;
	}

	public List<Boolean> areIngredientIncluded() {
		OrderView view = new OrderView(this.request);
		Integer[] contained = view.getParameterArray(TAG_CONTAINS_INGREDIENTS);
		List<Boolean> ingredientsIncluded = new ArrayList<Boolean>();
		for(Integer i : contained) {
			ingredientsIncluded.add(isIncluded(i));				
		}
		return ingredientsIncluded;
	}
	
	private Boolean isIncluded(Integer i) {
		return (i == 1) ? true : false;
	}

	public Integer[] getIds() {
		OrderView view = new OrderView(this.request);
		return view.getIds();
	}

}
