package es.restaurant.EatApp.Models;

import es.restaurant.EatApp.Models.User;

public class UserBuilder extends User {

	public UserBuilder() {
		super("empty", "empty");
	}

	public UserBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public UserBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public UserBuilder sergio() {
		return this.email("Sergio").password("mag1cPassW0rd!*");
	}
	
	public User build() {
		return new User(this.email, this.password);
	}
}
