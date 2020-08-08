package es.restaurant.EatApp.generalControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.repositories.IngredientDao;
import es.restaurant.EatApp.views.ShowIngredientsAmountView;

@Controller
public class ShowIngredientsAmountController {

	@GetMapping("/showIngredientsAmount")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		ShowIngredientsAmountView view = new ShowIngredientsAmountView(model, res);
		List<Ingredient> ingredients = IngredientDao.getIngredientDao().getUnderMinimumQuantityIngredients();
		return view.interact(ingredients);
	}
}
