package es.restaurant.EatApp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Table extends Observable {

	private int code;
	private List<Observer> observers;
	
	public Table(int code) {
		this.code = code;
		this.observers = new ArrayList<Observer>();
	}
	
	public void addObservers(Employee waiter) {
		this.observers.add(waiter);
	}
	
	public Table() {
		this.code = -1;
		this.observers = null;
	}
	public int getCode() {
		return this.code;
	}
	
	public void askForHelp() {
		this.setChanged();
		Notification notification = new NotificationBuilder(this.code).help().build();
		this.notifyObservers(notification);
	}

	public boolean equals(Table t) {
		return this.code == t.getCode();
	}

	public boolean isValid() {
		return this.code != -1;
	}
}