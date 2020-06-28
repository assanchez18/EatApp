package es.restaurant.EatApp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class UserJpa extends User{

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "Email")
	protected String email;
	@Column(name = "Password")
	protected String password;
	
	public UserJpa (String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public UserJpa() {
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
		return "User [" + this.id + ", " + this.email + ", " + this.password + "]";
	}
	
	@Override
	public boolean equals(User u) {
		return (this.email == u.getEmail() && this.password == u.getPassword());
	}
}
