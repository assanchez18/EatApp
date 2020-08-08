package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;

public class ShowIngredientsAmountView extends View {

	private static final String MANAGE_GROCERIES_VIEW = "showIngredientsAmountView";
	private static final String INGREDIENT_TAG = "ingredients";
	
	public ShowIngredientsAmountView(Model model, HttpServletResponse res) {
		super(model, res);
	}

	public String interact(List<Ingredient> ingredients) {
		this.model.addAttribute(INGREDIENT_TAG,ingredients);
		setStatusOk();
		return MANAGE_GROCERIES_VIEW;
	}
}
