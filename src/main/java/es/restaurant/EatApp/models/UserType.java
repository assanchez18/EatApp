package es.restaurant.EatApp.models;

public class UserType {

	public enum userType {
		ADMIN,
		COMMENSAL,
		WAITER,
		COOK
	}
	private userType type;
	
	public UserType(int type) {
		switch(type) {
		case 0:
			this.type = userType.ADMIN;
			break;
		case 1:
			this.type = userType.COMMENSAL;
			break;
		case 2:
			this.type = userType.WAITER;
			break;
		case 3:
			this.type = userType.COOK;
			break;
		default:
			this.type = userType.COMMENSAL;
			break;
		}
	}

	public UserType(userType type) {
		this.type = type;
	}
	
	public userType getType() {
		return this.type;
	}

	public String getTypeName() {
		return this.type.name();
	}

	public int getTypeOrdinal() {
		return this.type.ordinal();
	}

	public boolean equals(UserType u) {
		return getTypeOrdinal() == u.getTypeOrdinal();
	}
}