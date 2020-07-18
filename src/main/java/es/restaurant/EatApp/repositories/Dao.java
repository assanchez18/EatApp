package es.restaurant.EatApp.repositories;

public abstract class Dao {

	protected Database db;

	public Dao() {
		this.db = Database.getDatabase();
	}

	protected String selectAllFrom(String table) {
		return "SELECT * FROM " + table;		
	}

	protected String where(String condition) {
		return " WHERE " + condition;
	}

	protected String and(String condition) {
		return " AND " + condition;
	}
	
	protected String insertInto(String table, String condition) {
		return "INSERT INTO " + table + " " + condition;
	}

	protected String update(String table, String condition) {
		return "UPDATE " + table + " SET " + condition;
	}

	protected String delete(String table, String condition) {
		return "DELETE FROM " + table + condition;
	}

}
