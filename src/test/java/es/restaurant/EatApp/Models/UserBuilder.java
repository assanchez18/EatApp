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
	
	public UserBuilder type(userType type) {
		this.type = new UserType(type);
		return this;
	}
	
	public UserSql buildSQL() {
		return new UserSql(this.email, this.password, this.type);
	}

}
