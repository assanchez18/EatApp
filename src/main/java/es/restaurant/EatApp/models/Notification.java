package es.restaurant.EatApp.models;

public class Notification {

	public enum Type {
		HELP,
		MANAGE_PRODUCT_STATUS,//Se tendrá que dividir dependiendo de los estados
		CANCEL_PRODUCT,
		CANCEL_ORDER,
		MANAGE_GROCERIES, //Se divirá dependiendo de qué haga falta
		//Meteremos la de pagar, plato en preparación, plato cancelado.. etc
	}
	
	private Type type;
	private int owner;
	
	public Notification(Type type, int whoIsAskingFor) {
		this.type = type;
		this.owner = whoIsAskingFor;
	}

	public Type getType() {
		return this.type;
	}

	public int getOwner() {
		return this.owner;
	}

	public boolean compareId(int id) {
		return this.owner == id;
	}
}
