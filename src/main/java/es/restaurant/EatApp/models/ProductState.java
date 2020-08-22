package es.restaurant.EatApp.models;

public class ProductState {

	public enum productState {
		OPEN,
		QUEUED,
		COOKING,
		READY,
		SERVED,
		CANCELLED,
	}
	private productState state;

	public ProductState() {
		this.state = productState.OPEN;
	}
	
	public ProductState(productState state) {
		this.state = state;
	}
	
	public ProductState(int type) {
		switch(type) {
		case 0:
			this.state = productState.OPEN;
			break;
		case 1:
			this.state = productState.QUEUED;
			break;
		case 2:
			this.state = productState.COOKING;
			break;
		case 3:
			this.state = productState.READY;
			break;
		case 4:
			this.state = productState.SERVED;
			break;
		case 5:
			this.state = productState.CANCELLED;
			break;
		default:
			this.state = productState.OPEN;
			break;
		}
	}

	public productState getState() {
		return this.state;
	}

	public String getTypeName() {
		return this.state.name();
	}

	public int getTypeOrdinal() {
		return this.state.ordinal();
	}

	public boolean equals(ProductState u) {
		return getTypeOrdinal() == u.getTypeOrdinal();
	}

	public boolean isReady() {
		return this.state == productState.READY;
	}

	public boolean isCancelled() {
		return this.state == productState.CANCELLED;
	}
	
	public boolean isQueued() {
		return this.state == productState.QUEUED;
	}

	public void setAsOpen() {
		this.state = productState.OPEN;
	}

	public void setAsQueued() {
		this.state = productState.QUEUED;
	}

}