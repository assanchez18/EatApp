package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.Ingredient;

public class ProductIngredientsDao extends Dao {
	
	private static ProductIngredientsDao dao;
	private static final String TABLE_PRODUCT_INGREDIENTS_NAME = "product_ingredients";
	private static final String TAG_ID = "id";
	private static final String TAG_PRODUCT_ID = "productId";
	private static final String TAG_INGREDIENT_ID = "ingredientId";
	private static final String TAG_NAME = "name";
	private static final String TAG_DESCRIPTION = "description";
	
	
	public static ProductIngredientsDao getProductIngredientDao() {
		if(dao == null) {
			dao = new ProductIngredientsDao();
		}
		return dao;
	}

	private ProductIngredientsDao() {
		super();
	}

	private RowMapper<Ingredient> buildIngredient() {
		return new RowMapper<Ingredient>() {
			public Ingredient mapRow(ResultSet result, int rowNum) throws SQLException {
				Ingredient ingredient = new Ingredient(result.getInt("id"),
													   result.getString("name"),
													   result.getString("description"));
				return ingredient;
        	}
		};
	}
	
	/*QUERY = SELECT DISTINCT ingredients.id as id,
	                 ingredients.name as name,
	                 ingredients.description as description 
	                 FROM ingredients, product_ingredients     
	 */
	private String getIngredientsFromProductIngredients() {
		return select("DISTINCT " + IngredientDao.TABLE_INGREDIENTS_NAME + "." + TAG_ID   +  " as " + TAG_ID + ", "
				  + IngredientDao.TABLE_INGREDIENTS_NAME + "." + TAG_NAME + " as " + TAG_NAME + ", "
				  + IngredientDao.TABLE_INGREDIENTS_NAME + "." + TAG_DESCRIPTION + " as " + TAG_DESCRIPTION)
			  + from(IngredientDao.TABLE_INGREDIENTS_NAME +", " + TABLE_PRODUCT_INGREDIENTS_NAME);
	}
	
	/*QUERY = getIngredientsFromProductIngredients() +
	 *  WHERE product_ingredients.productId = productId 
	                   AND ingredients.id = product_ingredients.ingredientId
	 */
	public List<Ingredient> getIngredientsOfProduct(int productId) {
		String sql = getIngredientsFromProductIngredients()
				   + where(TABLE_PRODUCT_INGREDIENTS_NAME + "." + TAG_PRODUCT_ID + " = " + productId 
						   + and(IngredientDao.TABLE_INGREDIENTS_NAME + "." + TAG_ID + " = " + TABLE_PRODUCT_INGREDIENTS_NAME + "." + TAG_INGREDIENT_ID));
		List<Ingredient> ingredients = this.db.getJdbcTemplate().query(sql,buildIngredient());
		return ingredients.isEmpty() ? null : ingredients;
	}

	/*QUERY = getIngredientsFromProductIngredients() +
	 			WHERE ingredients.id NOT IN
 						(SELECT product_ingredients.ingredientId
 						 FROM product_ingredients
 						 WHERE product_ingredients.productId = productId)
	 */
	public List<Ingredient> getAllIngredientsUnlessOfProduct(int productId) {
		String sql = getIngredientsFromProductIngredients()
					+ where(IngredientDao.TABLE_INGREDIENTS_NAME + "." + TAG_ID + " NOT IN ("
								+ select(TABLE_PRODUCT_INGREDIENTS_NAME + "."+ TAG_INGREDIENT_ID)
									+ from(TABLE_PRODUCT_INGREDIENTS_NAME)
									+ where(TABLE_PRODUCT_INGREDIENTS_NAME + "." + TAG_PRODUCT_ID + " = " + productId) + ")");
		List<Ingredient> ingredients = this.db.getJdbcTemplate().query(sql,buildIngredient());
		return ingredients.isEmpty() ? null : ingredients;
	}

	public boolean updateIngredientsInProduct(List<Integer> newIngredientsId, int productId) {
		deleteAllIngredientsFromProduct(productId); 
		boolean isOk = true;
		for (Integer ingredientId : newIngredientsId) {
			//QUERY: INSERT INTO `product_ingredients`(`productId`, `ingredientId`) VALUES (productId, ingredientId)
			String sql = insertInto(TABLE_PRODUCT_INGREDIENTS_NAME, "(" + TAG_PRODUCT_ID + ", " + TAG_INGREDIENT_ID + ") VALUES (" + productId + ", " + ingredientId + ")");
			if(this.db.getJdbcTemplate().update(sql) == 0) {
				isOk = false;
			}
		}
		return isOk;
	}

	public boolean deleteAllIngredientsFromProduct(int productId) {
		//QUERY: DELETE FROM `product_ingredients` WHERE productId = productId
		String sql = delete(TABLE_PRODUCT_INGREDIENTS_NAME, where(TAG_PRODUCT_ID + " = " + productId));
		return !(this.db.getJdbcTemplate().update(sql) == 0);
	}
}
