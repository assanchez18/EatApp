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
	private Map<Integer, Table> usersInTables;
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
		this.usersInTables = new HashMap<Integer, Table>();
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
		return this.tables.getOrDefault(code, new Table(-1));
	}

	public void linkUserToTable(int userId, Table table) {
		this.usersInTables.put(userId, this.tables.get(table.getCode()));
	}

	public void unlinkUserToTable(int userId) {
		this.usersInTables.remove(userId);
	}

	public Table getTableWithUserId(int userId) {
		return this.usersInTables.getOrDefault(userId, new Table());
	}
}
