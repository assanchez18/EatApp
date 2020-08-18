package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.models.IngredientBuilder;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.models.ProductBuilder;

@RunWith(SpringRunner.class)
public class ProductIngredientsDaoTest {

	@Test
	public void modifyProductTest() {
		Product product = new ProductBuilder().build();
		Ingredient ingredientTest1 = new IngredientBuilder().build();
		Ingredient ingredientTest2 = new IngredientBuilder().id(-2).build();
		IngredientDao.getIngredientDao().insert(ingredientTest1);
		IngredientDao.getIngredientDao().insert(ingredientTest2);
		ProductDao.getProductDao().insertWithId(product);
		List<Integer> newIngredientsId = new ArrayList<Integer>();
		newIngredientsId.add(ingredientTest1.getId());
		newIngredientsId.add(ingredientTest2.getId());
		assertTrue("Error including new Ingredients in a product.", ProductIngredientsDao.getProductIngredientDao().updateIngredientsInProduct(newIngredientsId, product.getId()));
		assertFalse("Error no ingredients found in new product!", ProductIngredientsDao.getProductIngredientDao().getIngredientsOfProduct(product.getId()).isEmpty());
		assertFalse("Error no ingredients found in DB!", ProductIngredientsDao.getProductIngredientDao().getAllIngredientsUnlessOfProduct(product.getId()).isEmpty());
		assertTrue("Error removing the entry in product_ingredients table", ProductIngredientsDao.getProductIngredientDao().deleteAllIngredientsFromProduct(product.getId()));
		assertTrue("Error removing Ingredient 1", IngredientDao.getIngredientDao().deleteIngredient(ingredientTest1));
		assertTrue("Error removing Ingredient 2", IngredientDao.getIngredientDao().deleteIngredient(ingredientTest2));
		assertTrue("Error removing product", ProductDao.getProductDao().deleteProduct(product.getId()));
	}
	
}
