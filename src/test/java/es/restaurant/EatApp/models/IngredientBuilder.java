package es.restaurant.EatApp.models;

public class IngredientBuilder {

	private Ingredient ingredient;
	
	public IngredientBuilder() {
		//default ingredient in DB
		this.ingredient = new Ingredient(1, "ing1 Test", "testDesc", 1, 1);
	}

	public IngredientBuilder setMinimumAmount(double newAmount) {
		this.ingredient.setMinimumAmount(newAmount);
		return this;
	}
	
	public Ingredient build() {
		return this.ingredient;
	}
}
