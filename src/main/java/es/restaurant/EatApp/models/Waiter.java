package es.restaurant.EatApp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Waiter extends User implements Observer{

	private List<Observable> observables;
	private List<Notification> notifications;
	
	public Waiter() {
		this.observables = new ArrayList<Observable>();
		this.notifications = new ArrayList<Notification>();
	}

	@Override
	public void update(Observable o, Object notification) {
		this.notifications.add((Notification)notification);
	}
	
	public void addObserver() {
		for(Observable o : this.observables) {
			o.addObserver(this);
		}
	}
	
}
