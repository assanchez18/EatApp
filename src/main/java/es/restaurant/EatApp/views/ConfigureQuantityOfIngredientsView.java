package es.restaurant.EatApp.views;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import es.restaurant.EatApp.models.Ingredient;

public class ConfigureQuantityOfIngredientsView extends View {

	public final static String INGREDIENT_ID = "ingredientId";
	public final static String NEW_AMOUNT = "newAmount";
	
	public ConfigureQuantityOfIngredientsView(Model model, HttpServletResponse res) {
		super(model,res);
	}

	public ConfigureQuantityOfIngredientsView(Model model, HttpServletRequest req, HttpServletResponse res) {
		super(model, req, res);
	}

	public String interactGet(Collection<Ingredient> collection) {
		this.model.addAttribute("ingredients", collection);
		setStatusOk();
		return "manageIngredient";
	}
	
	public String interact() {
		setStatusOk();
		return "mainUserView";
	}
	
	public int getIngredientId() {
		return Integer.decode(this.request.getParameter(INGREDIENT_ID));
	}
	
	public double getNewAmount() {
		return Double.valueOf(this.request.getParameter(NEW_AMOUNT));
	}
}
