package es.restaurant.EatApp.views;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;

public class ShowIngredientsAmountView extends View {

	private static final String VIEW_MANAGE_GROCERIES = "showIngredientsAmountView";
	private static final String TAG_INGREDIENT = "ingredients";
	
	public ShowIngredientsAmountView(Model model, HttpServletResponse res) {
		super(model, res);
	}

	public String interact(List<Ingredient> ingredients) {
		this.model.addAttribute(TAG_INGREDIENT,ingredients);
		setStatusOk();
		return VIEW_MANAGE_GROCERIES;
	}
}
