package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.views.helpers.IngredientHelperView;

public class ShowIngredientsAmountView extends View {

	private final String VIEW_MANAGE_GROCERIES = "showIngredientsAmountView";
	private IngredientHelperView ingredientHelper;
	
	public ShowIngredientsAmountView(Model model, HttpServletResponse res) {
		super(model, res);
		this.ingredientHelper = new IngredientHelperView(model);
	}

	public String interact(List<Ingredient> ingredients) {
		this.ingredientHelper.addIngredients(ingredients);
		setStatusOk();
		return VIEW_MANAGE_GROCERIES;
	}
}
