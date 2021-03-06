package es.restaurant.EatApp.repositories;

import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class Database {

	private SimpleDriverDataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private static Database database = null;
	private final Object lock = new Object();
	
	public static Database getDatabase() {
		if (database == null) {
			database = new Database();			
		}
			return database;
	}
	
	private Database() {
    	this.dataSource = new SimpleDriverDataSource();
    	try {
			this.dataSource.setDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	//this.dataSource.setUrl("jdbc:mysql://localhost/eatappsqldb");
    	this.dataSource.setUrl("jdbc:mysql://localhost/fakerest1");
        this.dataSource.setUsername("root");
        this.dataSource.setPassword("root");
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		synchronized(lock) {
			return this.jdbcTemplate;
		}
	}

}
