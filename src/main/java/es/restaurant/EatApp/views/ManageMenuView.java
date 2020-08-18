package es.restaurant.EatApp.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.views.helpers.ParameterListReader;

public class ManageMenuView extends View {

	private static final String TAG_PRODUCTS = "products";
	private static final String TAG_ALL_INGREDIENTS = "allIngredients";
	public static final String TAG_CONTAINS_INGREDIENTS = "contains[]";

	private static final String MSG_NO_NAME_ERROR ="No has introducido nombre al producto";
	private static final String MSG_NO_DESCRIPTION_ERROR ="No has introducido description al producto";
	private static final String MSG_DB_ERROR ="No se ha podido realizar la acción sobre el producto en la base de datos";
	private static final String MSG_NO_INGREDIENTS_ERROR ="El producto que quieres modificar no tiene ingredientes!";

	private static final String VIEW_MANAGE_MENU = "manageMenuView";
	private static final String VIEW_PRODUCT = "showProduct";
	private static final String VIEW_CHANGE_PRODUCT_INGREDIENTS = "changeProductIngredients";

	private ProductView productView;
	private ParameterListReader parameterListReader;
	
	public ManageMenuView(Model model, HttpServletResponse res) {
		super(model,res);
		this.productView = new ProductView(model, res);
	}

	public ManageMenuView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model,req,res);
		this.productView = new ProductView(model, req, res);
		this.parameterListReader = new ParameterListReader(req);
	}

	public String showMenuWithOptions(Collection<Product> products) {
		this.model.addAttribute(TAG_PRODUCTS, products);
		setStatusOk();
		return VIEW_MANAGE_MENU;
	}

	public String showProductForm(Product product) {
		this.productView.prepareProductModel(product);
		setStatusOk();
		return VIEW_PRODUCT;
	}

	public String mainView() {
		setStatusOk();
		return VIEW_MAIN_USER;
	}

	public int getProductId() {
		return this.productView.getProductId();
	}

	public String getProductName() {
		return this.productView.getProductName();
	}

	public String getProductDescription() {
		return this.productView.getProductDescription();
	}

	public double getProductPrice() {
		return this.productView.getProductPrice();
	}

	public int getProductPriority() {
		return this.productView.getProductPriority();
	}

	public String errorNoProductName() {
		return returnErrorWithMessageAndErrorCode(MSG_NO_NAME_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}

	public String errorNoProductDescription() {
		return returnErrorWithMessageAndErrorCode(MSG_NO_DESCRIPTION_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}

	public String errorInDB() {
		return returnErrorWithMessageAndErrorCode(MSG_DB_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}

	public String errorNoIngredients() {
		return returnErrorWithMessageAndErrorCode(MSG_NO_INGREDIENTS_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}

	public String showIngredientsForm(Product product, List<Ingredient> allIngredients) {
		this.productView.prepareProductModel(product);
		this.model.addAttribute(TAG_ALL_INGREDIENTS, allIngredients);
		setStatusOk();
		return VIEW_CHANGE_PRODUCT_INGREDIENTS;
	}

	public List<Boolean> getIngredientStatus() {
		Integer[] contained = this.parameterListReader.getParameterArray(TAG_CONTAINS_INGREDIENTS);
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
		return this.parameterListReader.getIds();
	}
}
