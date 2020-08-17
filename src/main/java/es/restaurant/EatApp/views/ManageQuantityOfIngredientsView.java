package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;

public class ManageQuantityOfIngredientsView extends View {

	public final static String TAG_INGREDIENT_ID = "ingredientId";
	public final static String TAG_NEW_AMOUNT = "newAmount";
	private final static String MSG_ERROR = "El ingrediente no existe.";
	private final static String VIEW_MANAGE_INGREDIENT = "manageIngredient";

	public ManageQuantityOfIngredientsView(Model model, HttpServletResponse res) {
		super(model,res);
	}

	public ManageQuantityOfIngredientsView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public String showIngredientsToManage(Collection<Ingredient> collection) {
		this.model.addAttribute("ingredients", collection);
		setStatusOk();
		return VIEW_MANAGE_INGREDIENT;
	}

	public String interact() {
		setStatusOk();
		return VIEW_MAIN_USER;
	}

	public int getIngredientId() {
		return Integer.decode(this.request.getParameter(TAG_INGREDIENT_ID));
	}

	public double getNewAmount() {
		return Double.valueOf(this.request.getParameter(TAG_NEW_AMOUNT));
	}

	public String errorIngredientNotFound() {
		return returnErrorWithMessage(MSG_ERROR, HttpServletResponse.SC_BAD_REQUEST, VIEW_ERROR);
	}
}
