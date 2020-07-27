package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;

public class ConfigureQuantityOfIngredientsView extends View {

	public final static String INGREDIENT_ID = "ingredientId";
	public final static String NEW_AMOUNT = "newAmount";
	private final static String ERROR_MSG = "El ingrediente no existe.";
	private final static String MANAGE_INGREDIENT_VIEW = "manageIngredient";

	public ConfigureQuantityOfIngredientsView(Model model, HttpServletResponse res) {
		super(model,res);
	}

	public ConfigureQuantityOfIngredientsView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public String interactGet(Collection<Ingredient> collection) {
		this.model.addAttribute("ingredients", collection);
		setStatusOk();
		return MANAGE_INGREDIENT_VIEW;
	}

	public String interact() {
		setStatusOk();
		return MAIN_USER_VIEW;
	}

	public int getIngredientId() {
		return Integer.decode(this.request.getParameter(INGREDIENT_ID));
	}

	public double getNewAmount() {
		return Double.valueOf(this.request.getParameter(NEW_AMOUNT));
	}

	public String error() {
		return returnErrorWithMessage(ERROR_MSG, HttpServletResponse.SC_BAD_REQUEST, ERROR_VIEW);
	}
}
