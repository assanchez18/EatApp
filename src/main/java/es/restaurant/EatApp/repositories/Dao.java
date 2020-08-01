package es.restaurant.EatApp.repositories;

public abstract class Dao {

	protected Database db;

	public Dao() {
		this.db = Database.getDatabase();
	}

	protected String selectAllFrom(String dbTable) {
		return "SELECT * FROM " + dbTable;		
	}

	protected String where(String condition) {
		return " WHERE " + condition;
	}

	protected String and(String condition) {
		return " AND " + condition;
	}
	
	protected String or(String condition) {
		return " OR " + condition;
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
