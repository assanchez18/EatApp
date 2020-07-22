package es.restaurant.EatApp.models;

public class ProductPriority {

	public enum productPriority {
		FIRST,
		SECOND,
		DRINK,
		DESSERT
	}
	private productPriority priority;

	public ProductPriority(int type) {
		switch(type) {
		case 0:
			this.priority = productPriority.FIRST;
			break;
		case 1:
			this.priority = productPriority.SECOND;
			break;
		case 2:
			this.priority = productPriority.DRINK;
			break;
		case 3:
			this.priority = productPriority.DESSERT;
			break;
		default:
			this.priority = productPriority.FIRST;
			break;
		}
	}

	public ProductPriority(productPriority priority) {
		this.priority = priority;
	}

	public productPriority getPriority() {
		return this.priority;
	}

	public String getTypeName() {
		return this.priority.name();
	}

	public int getTypeOrdinal() {
		return this.priority.ordinal();
	}

	public boolean equals(ProductPriority u) {
		return getTypeOrdinal() == u.getTypeOrdinal();
	}
}