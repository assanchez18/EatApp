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
		Ingredient ingredient = new IngredientBuilder().baseIngredient().build();
		assertTrue("Error, base ingredient not found",dao.findIngredient(ingredient).equals(ingredient));
	}
	
	@Test
	public void modifyMinimumAmountForIngredientTest() {
		Ingredient ingredient = new IngredientBuilder().build();
		IngredientDao dao = IngredientDao.getIngredientDao();
		Ingredient updatedIngredient = new IngredientBuilder()
												.name("newName")
												.amount(7)
												.description("newDescription")
												.minAmount(10).build();
		//insert
		assertTrue("Error, cannot insert a new Ingredient", dao.insert(ingredient));
		//modify
		assertTrue("Error updating minimumAmount", dao.updateMinimumAmount(ingredient, updatedIngredient.getMinimumAmount()));
		assertTrue("Error updating name", dao.updateName(ingredient, updatedIngredient.getName()));
		assertTrue("Error updating description", dao.updateDescription(ingredient,updatedIngredient.getDescription()));
		assertTrue("Error updating amount", dao.updateAmount(ingredient, updatedIngredient.getAmount()));
		//remove
		assertTrue("Error, unable to delete the ingredient", dao.deleteIngredient(ingredient));
	}
}
