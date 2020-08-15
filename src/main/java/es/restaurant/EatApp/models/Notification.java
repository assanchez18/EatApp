package es.restaurant.EatApp.models;

public class Notification {

	public enum Type {
		HELP,
		// Order status
		ORDER_QUEUED,//More Order States whith ManageOrderStatus UC
		ORDER_CANCELLED,
		// Product Status
		PRODUCT_READY,
		PRODUCT_CANCELLED,
		MANAGE_GROCERIES, //Se divirá dependiendo de qué haga falta
		//Meteremos la de pagar, plato en preparación, plato cancelado.. etc
	}
	
	private Type type;
	private int owner;

	public Notification(Type type, int whoIsAskingFor) {
		this.type = type;
		this.owner = whoIsAskingFor;
	}

	public Notification(Product product, int whoIsAskingFor) {
		if(product.isReady()) {
			this.type = Type.PRODUCT_READY;
		} else if(product.isCancelled()) {
			this.type = Type.PRODUCT_CANCELLED;
		}
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
