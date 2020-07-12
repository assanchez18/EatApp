package es.restaurant.EatApp.models.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.Table;

public class TableDao {
	private Database db;
	private Map<Integer, Table> tables;
	private static TableDao dao;
	
	public static TableDao getTableDao() {
		if(dao == null) {
			dao = new TableDao();
		}
		return dao;
	}
	
	private TableDao() {
		this.db = Database.getDatabase();
		this.tables = new HashMap<Integer, Table>();
		loadTables();
	}
	
	private void loadTables() {
		for(Table t : executeQuery(selectAllTables())) {
			this.tables.put(t.getCode(), t);
		}
	}
	
	private RowMapper<Table> buildTable() {
		return new RowMapper<Table>() {
			public Table mapRow(ResultSet result, int rowNum) throws SQLException {
				Table table = new Table(result.getInt("code"));
				return table;
        	}
		};
	}
	
	public String selectAllTables() {
		return "SELECT * FROM tables";
	}
	
	public List<Table> executeQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildTable());
	}
	
	public Map<Integer, Table> getTables() {
		return this.tables;
	}
	
	public Table getTable(int code) {
		return this.tables.get(code);
	}
}
