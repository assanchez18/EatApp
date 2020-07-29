package es.restaurant.EatApp.models;

public class MenuBuilder {

	private Menu menu;
	
	public MenuBuilder() {
		this.menu = new Menu(-1L, "emtpy");
	}

	public MenuBuilder description(String description) {
		this.menu.setDescription(description);
		return this;
	}
	
	public MenuBuilder testMenu() {
		this.menu = new Menu(1L, "Menu de prueba");
		return this;
	}
	
	public Menu build() {
		return this.menu;
	}

}
