package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.repositories.IngredientDao;
import es.restaurant.EatApp.views.ConfigureQuantityOfIngredientsView;

@Controller
public class ConfigureQuantityOfIngredientsController implements ControllerInterface {

	@GetMapping("/configureQuantityOfIngredients")
	public String controlGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		ConfigureQuantityOfIngredientsView view = new ConfigureQuantityOfIngredientsView(model, res);
		return view.interactGet(IngredientDao.getIngredientDao().getIngredients());
	}

	@PostMapping("/manageQuantityOfIngredient")
	public String control(Model model, HttpServletRequest req, HttpServletResponse res) {
		ConfigureQuantityOfIngredientsView view = new ConfigureQuantityOfIngredientsView(model, req, res);
		Ingredient ingredient = IngredientDao.getIngredientDao().getIngredientById(view.getIngredientId());
		IngredientDao.getIngredientDao().updateMinimumAmount(ingredient, view.getNewAmount()); // TODO SECURE THIS IF THE ID IS NOT IN DATABASE
		return view.interact();
	}
}