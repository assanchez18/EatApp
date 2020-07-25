package es.restaurant.EatApp.models;

public class OrderState {

	public enum orderState {
		IN_PROGRESS,
		QUEUED,
		COOKING,
		FINISHED,
		CANCELLED,
		PAYED,
		REVIEWED
	}
	private orderState state;

	public OrderState() {
		this.state = orderState.IN_PROGRESS;
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