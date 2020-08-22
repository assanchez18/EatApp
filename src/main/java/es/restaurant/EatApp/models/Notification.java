package es.restaurant.EatApp.models;

public class Notification {

	private enum Type {
		HELP,
		// Order status
		ORDER_QUEUED,//More Order States whith ManageOrderStatus UC
		ORDER_CANCELLED,
		// Product Status
		PRODUCT_QUEUED,
		PRODUCT_READY,
		PRODUCT_CANCELLED,
		MANAGE_GROCERIES, //Se divirá dependiendo de qué haga falta
		//Meteremos la de pagar, plato en preparación, plato cancelado.. etc
	}
	
	private Type type;
	private int owner;

	public Notification(int whoIsAskingFor) {
		this.type = null;
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

	public void orderQueued() {
		this.type = Type.ORDER_QUEUED;
	}

	public void help() {
		this.type = Type.HELP;
	}

	public void productReady() {
		this.type = Type.PRODUCT_READY;
	}

	public void productCancelled() {
		this.type = Type.PRODUCT_CANCELLED;
	}

	public void productQueued() {
		this.type = Type.PRODUCT_QUEUED;
	}
}
