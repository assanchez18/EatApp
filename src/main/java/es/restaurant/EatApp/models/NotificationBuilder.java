package es.restaurant.EatApp.models;

public class NotificationBuilder {

	private Notification notification;
	
	public NotificationBuilder(int tableCode) {
		this.notification = new Notification(tableCode);
	}
	
	public NotificationBuilder(Product product, int tableCode) {
		this.notification = new Notification(tableCode);
		if(product.isReady()) {
			this.notification.productReady();
		} else if(product.isCancelled()) {
			this.notification.productCancelled();
		} else if(product.isQueued()) {
			this.notification.productQueued();
		}
	}
	
	public Notification build() {
		return this.notification;
	}

	public NotificationBuilder orderQueued() {
		this.notification.orderQueued();
		return this;
	}

	public NotificationBuilder help() {
		this.notification.help();
		return this;
	}
}
