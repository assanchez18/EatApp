package es.restaurant.EatApp.models;

public class MenuBuilder {

	private String description;
	
	public MenuBuilder() {
		this.description = "empty";
	}

	public MenuBuilder description(String description) {
		this.description = description;
		return this;
	}
	
	public MenuBuilder testMenu() {
		return this.description("Menu de prueba");
	}
	
	public Menu build() {
		return new Menu(this.description);
	}

}
