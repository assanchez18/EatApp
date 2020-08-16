package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.Table;

public class TableDao extends Dao{
	
	private Map<Integer, Table> tables;
	private Map<Integer, Integer> usersInTables;
	private static TableDao dao;
	private static final String TABLE_NAME = "tables";

	public static TableDao getTableDao() {
		if(dao == null) {
			dao = new TableDao();
		}
		return dao;
	}

	private TableDao() {
		super();
		this.tables = new HashMap<Integer, Table>();
		this.usersInTables = new HashMap<Integer, Integer>();
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
		return selectAllFrom(TABLE_NAME);
	}
	
	public String selectAllTables(String condition) {
		return selectAllFrom(TABLE_NAME) + where(condition);
	}


	public List<Table> executeQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildTable());
	}

	public Set<Integer> getTablesNumbers() {
		return this.tables.keySet();
	}

	public Collection<Table> getTables() {
		return this.tables.values();
	}

	public Table getTable(int code) {
		return this.tables.get(code);
	}

	public void linkUserToTable(int userId, int tableId) {
		usersInTables.put(userId, tableId);
	}

	public void unlinkUserToTable(int userId) {
		usersInTables.remove(userId);
	}

	public int getTableWithUserId(int userId) {
		return usersInTables.getOrDefault(userId, 0);
	}
}
