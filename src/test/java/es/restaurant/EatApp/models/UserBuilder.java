package es.restaurant.EatApp.models;

import es.restaurant.EatApp.models.UserType.userType;

public class UserBuilder {

	private String email;
	private String password;
	private UserType type;
	
	public UserBuilder() {
		this.email = "empty@empty";
		this.password = "empty";
		this.type = new UserType(userType.COMMENSAL);
	}
	
	public UserBuilder employee() {
		this.email = "test@test.com";
		this.password = "test";
		this.type = new UserType(userType.WAITER);
		return this;
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
		return this.email("sergio@admin.com").password("mag1cPassW0rd!*").type(userType.ADMIN);
	}
	
	public UserBuilder admin() {
		return this.email("admin@admin.com").password("admin").type(userType.ADMIN);
	}
	
	public UserBuilder waiter() {
		return this.email("waiter@waiter.com").password("waiter").type(userType.WAITER);
	}
	
	public UserBuilder commensal() {
		return this.email("comensal@comensal.com").password("commensal").type(userType.COMMENSAL);
	}
		
	public UserBuilder type(userType type) {
		this.type = new UserType(type);
		return this;
	}
	
	public User build() {
		return new User(this.email, this.password, this.type);
	}

}
