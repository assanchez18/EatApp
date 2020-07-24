package es.restaurant.EatApp.models;

public class Ingredient {

	private int id;
	private String name;
	private String description;
	private double amount;
	private double minimumAmount;
	
	public Ingredient(int id, String name, String description, double amount, double minimumAmount) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.minimumAmount = minimumAmount;
	}
	
	public void setMinimumAmount(double newAmount) {
		this.minimumAmount = newAmount;
	}

	public int getId() {
		return this.id;
	}
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getAmount() {
		return amount;
	}

	public double getMinimumAmount() {
		return minimumAmount;
	}

	public boolean equals(Ingredient i) {
		return (this.id == i.getId()
				&& this.name.compareTo(i.getName()) == 0
				&& this.description.compareTo(i.getDescription()) == 0
				&& this.amount == i.getAmount()
				&& this.minimumAmount == i.minimumAmount);
	}

	public void setName(String n) {
		this.name = n;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}