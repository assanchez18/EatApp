package es.restaurant.EatApp.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Waiter extends User implements Observer{

	private List<Notification> notifications;
	
	public Waiter() {
		this.notifications = new ArrayList<Notification>();
	}
	
	public Waiter (Long id, String email, String password, int type) {
		super(id, email, password,type);
		this.notifications = new ArrayList<Notification>();
	}

	@Override
	public void update(Observable o, Object notification) {
		this.notifications.add((Notification)notification);
	}
	
	public void addObserver(Collection<Table> tables) {
		for(Observable o : tables) {
			o.addObserver(this);
		}
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void completeNotification(int notificationId) {
		for(int i = 0; i < this.notifications.size(); i++) {
			if(this.notifications.get(i).compareId(notificationId)) {
				this.notifications.remove(i);
				break;
			}
		}
	}
}
