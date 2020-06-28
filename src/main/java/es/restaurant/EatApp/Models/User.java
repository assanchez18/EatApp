package es.restaurant.EatApp.models;

public abstract class User {

	public abstract Long getId();

	public abstract String getEmail();

	public abstract String getPassword();

	public abstract boolean equals(User u);
}
