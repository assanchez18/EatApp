package es.restaurant.EatApp.models;

import es.restaurant.EatApp.models.UserType.userType;

public class User {

	protected int id;
	protected String email;
	protected String password;
	protected UserType type;
	
	public User (int id, String email, String password, int type) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.type = new UserType(type);
	}

	public User (int id, String email, String password, UserType type) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public User (String email, String password, int type) {
		this.id = -1;
		this.email = email;
		this.password = password;
		this.type = new UserType(type);
	}
	
	public User (String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User() {
		this.email = "";
		this.password = "";
		this.type = new UserType(userType.COMMENSAL);
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public String toString() {
		return "UserSql [id=" + this.id + ", email=" + this.email +
				", password=" + this.password + ", type=" + this.type + "]";
	}

	public boolean equals(User u) {
		return (this.email.compareTo(u.getEmail())== 0 
				&& this.password.compareTo(u.getPassword())== 0)
				&& this.type.equals(u.getUserType());
	}
	
	public UserType getUserType() {
		return this.type;
	}

	public int getId() {
		return this.id;
	}

	public boolean isCommensal() {
		return this.type.isCommensal();
	}

}
