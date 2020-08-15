package es.restaurant.EatApp.models;

public class OrderState {

	public enum orderState {
		OPEN,
		QUEUED,
		COOKING,
		READY,
		FINISHED,
		CANCELLED,
		PAYED,
		REVIEWED,
		BASE
	}
	private orderState state;

	public OrderState() {
		this.state = orderState.BASE;
	}

	public OrderState(int state) {
		switch(state) {
		case 0:
			this.state = orderState.OPEN;
			break;
		case 1:
			this.state = orderState.QUEUED;
			break;
		case 2:
			this.state = orderState.COOKING;
			break;
		case 3:
			this.state = orderState.READY;
			break;
		case 4:
			this.state = orderState.FINISHED;
			break;
		case 5:
			this.state = orderState.CANCELLED;
			break;
		case 6:
			this.state = orderState.PAYED;
			break;
		case 7:
			this.state = orderState.REVIEWED;
			break;
		case 8:
			this.state = orderState.BASE;
			break;
		default:
			this.state = orderState.BASE;
			break;
		}
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

	public boolean isOpen() {
		return this.state == orderState.OPEN;
	}
}