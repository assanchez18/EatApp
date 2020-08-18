package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.views.helpers.IngredientHelperView;

public class ManageQuantityOfIngredientsView extends View {

	public final static String TAG_NEW_AMOUNT = "newAmount";
	private final String MSG_ERROR = "El ingrediente no existe.";
	private final String VIEW_MANAGE_INGREDIENT = "manageIngredient";
	private IngredientHelperView ingredientHelper;
	
	public ManageQuantityOfIngredientsView(Model model, HttpServletResponse res) {
		super(model,res);
		this.ingredientHelper = new IngredientHelperView(model);
	}

	public ManageQuantityOfIngredientsView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
		this.ingredientHelper = new IngredientHelperView(model, req);
	}

	public String showIngredientsToManage(Collection<Ingredient> ingredients) {
		this.ingredientHelper.addIngredients(ingredients);
		setStatusOk();
		return VIEW_MANAGE_INGREDIENT;
	}

	public String interact() {
		setStatusOk();
		return VIEW_MAIN_USER;
	}

	public int getIngredientId() {
		return this.ingredientHelper.getIngredientId();
	}

	public double getNewAmount() {
		return Double.valueOf(this.request.getParameter(TAG_NEW_AMOUNT));
	}

	public String errorIngredientNotFound() {
		return returnErrorWithMessageAndErrorCode(MSG_ERROR, HttpServletResponse.SC_BAD_REQUEST);
	}
}
