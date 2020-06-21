package es.restaurant.EatApp.Models.Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import es.restaurant.EatApp.Models.UserSql;

public class UserSqlDao {
	
	private Database db;
	
	public UserSqlDao() {
		this.db = Database.getDatabase();
	}

	public boolean verifyUser(UserSql user) {
		String sql = "Select * from user where user.email=\""+ user.getEmail()+ "\" and user.password=\"" + user.getPassword() + "\"";
		return !executeQuery(sql).isEmpty();
	}
	
	private RowMapper<UserSql> getUser() {
		return new RowMapper<UserSql>() {
			public UserSql mapRow(ResultSet result, int rowNum) throws SQLException {
				UserSql user = new UserSql(result.getLong("id"),result.getString("email"),result.getString("password"));
				return user;
        	}
		};
	}
	
	public List<UserSql> executeQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, getUser());
	}
	
	public boolean insert(UserSql user) {
		String sql = "INSERT INTO user (email, password) VALUES (?,?)";
		return (this.db.getJdbcTemplate().update(sql,user.getEmail(),user.getPassword()) == 1);
	}
	
	public boolean updatePassword(UserSql currentUser, String newPassword) {
        String sql = "UPDATE user SET password=? WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, newPassword, currentUser.getEmail()) == 1);
	}
	
	public boolean updateEmail(UserSql currentUser, String newEmail) {
        String sql = "UPDATE user SET email=? WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, newEmail, currentUser.getEmail()) == 1);
	}
	
	public boolean deleteUser(UserSql user) {
        String sql = "DELETE FROM user WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, user.getEmail()) == 1);
	}
}
