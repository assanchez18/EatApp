package es.restaurant.EatApp.models;

public class OrderState {

	public enum orderState {
		BASE,
		OPEN,
		QUEUED,
		COOKING,
		FINISHED,
		CANCELLED,
		PAYED,
		REVIEWED
	}
	private orderState state;

	public OrderState() {
		this.state = orderState.BASE;
	}

	public OrderState(orderState state) {
		this.state = state;
	}

	public orderState getState() {
		return this.state;
	}

	public String getTypeName() {
		return this.state.name();
	}

	public int getTypeOrdinal() {
		return this.state.ordinal();
	}

	public boolean equals(OrderState u) {
		return getTypeOrdinal() == u.getTypeOrdinal();
	}
}