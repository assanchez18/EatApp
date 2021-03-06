package es.restaurant.EatApp.generalControllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.repositories.IngredientDao;
import es.restaurant.EatApp.views.ManageQuantityOfIngredientsView;

@Controller
public class ManageQuantityOfIngredientsController {

	@GetMapping("/manageQuantityOfIngredient")
	public String showIngredientsToManage(Model model, HttpServletRequest req, HttpServletResponse res) {
		ManageQuantityOfIngredientsView view = new ManageQuantityOfIngredientsView(model, res);
		return view.showIngredientsToManage(IngredientDao.getIngredientDao().getIngredients());
	}

	
	@PostMapping("/manageQuantityOfIngredient")
	public String manageQuantityOfIngredient(Model model, HttpServletRequest req, HttpServletResponse res) {
		ManageQuantityOfIngredientsView view = new ManageQuantityOfIngredientsView(model, req, res);
		Ingredient ingredient = IngredientDao.getIngredientDao().findIngredient(view.getIngredientId());
		if(ingredient == null) {
			return view.errorIngredientNotFound();
		}
		IngredientDao.getIngredientDao().updateMinimumAmount(ingredient, view.getNewAmount());
		return view.interact();
	}
}