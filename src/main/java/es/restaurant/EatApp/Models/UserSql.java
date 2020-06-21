package es.restaurant.EatApp.Models;

public class UserSql extends User{

	private Long id;
	protected String email;
	protected String password;
	
	public UserSql (Long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	public UserSql (String email, String password) {
		this.id = -1L;
		this.email = email;
		this.password = password;
	}
	public UserSql() {
		this.email = "";
		this.password = "";
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
		     && this.password.compareTo(u.getPassword())== 0);
	}

}
