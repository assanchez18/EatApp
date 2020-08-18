package es.restaurant.EatApp.views.helpers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;

public class IngredientHelperView {

	private final String TAG_INGREDIENT = "ingredients";
	public final static String TAG_INGREDIENT_ID = "ingredientId";
	private Model model;
	private HttpServletRequest request;

	public IngredientHelperView(Model model) {
		this.model = model;
		this.request = null;
	}

	public IngredientHelperView(Model model, HttpServletRequest req) {
		this.model = model;
		this.request = req;
	}
	
	public void addIngredients(Collection<Ingredient> ingredients) {
		this.model.addAttribute(TAG_INGREDIENT,ingredients);		
	}

	public int getIngredientId() {
		return Integer.decode(this.request.getParameter(TAG_INGREDIENT_ID));
	}
	
	
}
