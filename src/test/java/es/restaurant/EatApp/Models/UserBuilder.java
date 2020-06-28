package es.restaurant.EatApp.models;

import es.restaurant.EatApp.models.UserJpa;

public class UserBuilder {

	private String email;
	private String password;
	
	public UserBuilder() {
		this.email = "empty@empty";
		this.password = "empty";
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
		return this.email("sergio@admin.com").password("mag1cPassW0rd!*");
	}
	
	public UserBuilder admin() {
		return this.email("admin@admin.com").password("admin");
	}
	
	public UserJpa buildJPA() {
		return new UserJpa(this.email, this.password);
	}
	public UserSql buildSQL() {
		return new UserSql(this.email, this.password);
	}

}
