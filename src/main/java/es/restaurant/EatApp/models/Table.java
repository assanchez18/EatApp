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
	
	public void addObservers(Waiter waiter) {
		this.observers.add(waiter);
	}
	
	public int getCode() {
		return this.code;
	}
	
	public void askForHelp() {
		this.setChanged();
		this.notifyObservers(new Notification(Notification.Type.HELP, this.code));
	}

	public boolean equals(Table t) {
		return this.code == t.getCode();
	}
}