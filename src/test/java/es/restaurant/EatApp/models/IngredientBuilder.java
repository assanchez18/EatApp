package es.restaurant.EatApp.models;

public class IngredientBuilder {

	private Ingredient ingredient;
	
	public IngredientBuilder() {
		this.ingredient = new Ingredient(-1, "Test", "testDesc", 1, 1);
	}

	public IngredientBuilder minAmount(double newAmount) {
		this.ingredient.setMinimumAmount(newAmount);
		return this;
	}

	public IngredientBuilder id(int newId) {
		this.ingredient.setId(newId);
		return this;
	}

	
	public IngredientBuilder baseIngredient() {
		//default ingredient in DB
		this.ingredient = new Ingredient(1, "ing1 Test", "testDesc", 1, 1);
		return this;
	}
	
	public IngredientBuilder name(String n) {
		this.ingredient.setName(n);
		return this;
	}
	
	public IngredientBuilder description(String n) {
		this.ingredient.setDescription(n);
		return this;
	}
	
	public IngredientBuilder amount(double n) {
		this.ingredient.setAmount(n);
		return this;
	}
	
	public Ingredient build() {
		return this.ingredient;
	}
}
