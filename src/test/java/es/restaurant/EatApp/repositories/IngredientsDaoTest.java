package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.models.IngredientBuilder;


@RunWith(SpringRunner.class)
public class IngredientsDaoTest {
	@Test
	public void selectIngredientsTest() {
		IngredientDao dao = IngredientDao.getIngredientDao();
		assertFalse("Error, missing base ingredient", dao.getIngredients().isEmpty());
	}
	
	@Test
	public void modifyMinimumAmountForIngredientTest() {
		Ingredient ingredient = new IngredientBuilder().build();
		Ingredient updatedIngredient = new IngredientBuilder().build();
		double newMinimumAmount = 10;
		updatedIngredient.setMinimumAmount(newMinimumAmount);
		IngredientDao dao = IngredientDao.getIngredientDao();
		assertTrue("Error, missing base ingredient", dao.updateMinimumAmount(ingredient, newMinimumAmount));
		assertFalse("Error, minimumAmount has not been updated", ingredient.equals(dao.findIngredient(ingredient)));
		assertTrue("Error, minimumAmount has not been updated to the correct value", updatedIngredient.equals(dao.findIngredient(ingredient)));
		assertTrue("Error restoring ingredient to initial value", dao.updateMinimumAmount(ingredient, ingredient.getMinimumAmount()));
	}
}
