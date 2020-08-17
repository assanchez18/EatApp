package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.Ingredient;

public class IngredientDao extends Dao {
	
	private static IngredientDao dao;
	public static final String TABLE_INGREDIENTS_NAME = "ingredients";
	
	public static IngredientDao getIngredientDao() {
		if(dao == null) {
			dao = new IngredientDao();
		}
		return dao;
	}

	private IngredientDao() {
		super();
	}

	private RowMapper<Ingredient> buildIngredient() {
		return new RowMapper<Ingredient>() {
			public Ingredient mapRow(ResultSet result, int rowNum) throws SQLException {
				Ingredient ingredient = new Ingredient(result.getInt("id"),
													   result.getString("name"),
													   result.getString("description"),
													   result.getDouble("amount"),
													   result.getDouble("minimumAmount"));
				return ingredient;
        	}
		};
	}

	public String selectAllIngredients() {
		return selectAllFrom(TABLE_INGREDIENTS_NAME);
	}
	public List<Ingredient> executeQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildIngredient());
	}
	
	public List<Ingredient> getIngredients() {
		return executeQuery(selectAllIngredients());
	}

	public boolean updateMinimumAmount(Ingredient ingredient, double newValue) {
		String sql = update(TABLE_INGREDIENTS_NAME, "minimumAmount=?" + where("id=?"));
        ingredient = this.findIngredient(ingredient);
        ingredient.setMinimumAmount(newValue);
        return updateIngredient(sql, ingredient, Double.toString(newValue));
	}

	public Ingredient findIngredient(Ingredient ingredient) {
		return findIngredient(ingredient.getId());
	}

	public Ingredient findIngredient(int id) {
		List<Ingredient> i = executeQuery(selectAllIngredients() + where("id=" + id));
		if(i.size() == 0)
			return null;
		else
			return i.get(0);
	}

	public boolean insert(Ingredient ingredient) {
		if(findIngredient(ingredient.getId()) == null) {
			String sql = insertInto(TABLE_INGREDIENTS_NAME, "(id, name, amount, description, minimumAmount) VALUES (?,?,?,?,?)");
			if (this.db.getJdbcTemplate().update(sql, ingredient.getId(),
													  ingredient.getName(),
													  ingredient.getAmount(),
													  ingredient.getDescription(),
													  ingredient.getMinimumAmount()) == 1) {
				return true;
			}
		}
		return false;
	}

	public boolean deleteIngredient(Ingredient ingredient) {
        String sql = delete(TABLE_INGREDIENTS_NAME, where("id=?"));
        if (this.db.getJdbcTemplate().update(sql, ingredient.getId()) == 1) {
        	return true;
        }
        return false;
	}
	
	public boolean updateName(Ingredient ingredient, String newValue) {
        String sql = update(TABLE_INGREDIENTS_NAME, "name=?" + where("id=?"));
        ingredient = this.findIngredient(ingredient);
        ingredient.setName(newValue);
        return updateIngredient(sql, ingredient, newValue);
	}

	public boolean updateDescription(Ingredient ingredient, String newValue) {
        String sql = update(TABLE_INGREDIENTS_NAME, "description=?" + where("id=?"));
        ingredient = this.findIngredient(ingredient);
        ingredient.setDescription(newValue);
        return updateIngredient(sql, ingredient, newValue);
	}

	public boolean updateAmount(Ingredient ingredient, double newValue) {
        String sql = update(TABLE_INGREDIENTS_NAME, "amount=?" + where("id=?"));
        ingredient = this.findIngredient(ingredient);
        ingredient.setAmount(newValue);
        return updateIngredient(sql, ingredient, Double.toString(newValue));
	}

	private boolean updateIngredient(String sql, Ingredient ingredient, String newValue) {
		if( this.db.getJdbcTemplate().update(sql, newValue, ingredient.getId()) == 1) {
			return true;
		}
		return false;
	}

	public String underMinimumQuantityIngredientsQuery() {
		return selectAllIngredients() + where(" amount <= minimumAmount ");
	}
	
	public List<Ingredient> getUnderMinimumQuantityIngredients() {
		return executeQuery(underMinimumQuantityIngredientsQuery());
	}
	
	public List<Ingredient> getUnderMinimumQuantityIngredients(String condition) {
		return executeQuery(underMinimumQuantityIngredientsQuery() + condition);
	}
}
