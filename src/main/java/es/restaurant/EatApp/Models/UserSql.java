package es.restaurant.EatApp.models;

import es.restaurant.EatApp.models.UserType.userType;

public class UserSql extends User{

	private Long id;
	protected String email;
	protected String password;
	protected UserType type;
	
	public UserSql (Long id, String email, String password, int type) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.type = new UserType(type);
	}

	public UserSql (Long id, String email, String password, UserType type) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public UserSql (String email, String password, UserType type) {
		this.email = email;
		this.password = password;
		this.type = type;
	}
	public UserSql (String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public UserSql() {
		this.email = "";
		this.password = "";
		this.type = new UserType(userType.COMMENSAL);
	}
	
	public Long getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}
	
	@Override
	public String toString() {
		return "User [" + this.id + ", " + this.email + ", " + this.password + "]\n";
	}
	
	@Override
	public boolean equals(User u) {
		return (this.email.compareTo(u.getEmail())== 0 
				&& this.password.compareTo(u.getPassword())== 0)
				&& this.type.equals(u.getUserType());
	}
	@Override
	public UserType getUserType() {
		return this.type;
	}

}
