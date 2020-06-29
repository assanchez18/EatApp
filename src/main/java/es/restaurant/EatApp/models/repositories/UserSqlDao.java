package es.restaurant.EatApp.models.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import es.restaurant.EatApp.models.UserSql;
import es.restaurant.EatApp.models.UserType;

public class UserSqlDao {
	
	private Database db;
	
	public UserSqlDao() {
		this.db = Database.getDatabase();
	}

	public boolean verifyUser(UserSql user) {
		String sql = "Select * from user where user.email=\""+ user.getEmail()+ "\" and user.password=\"" + user.getPassword() + "\"";
		return !executeQuery(sql).isEmpty();
	}
	
	private RowMapper<UserSql> buildUser() {
		return new RowMapper<UserSql>() {
			public UserSql mapRow(ResultSet result, int rowNum) throws SQLException {
				UserSql user = new UserSql(result.getLong("id"),result.getString("email"),result.getString("password"), result.getInt("type"));
				return user;
        	}
		};
	}
	
	public List<UserSql> executeQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildUser());
	}
	
	public boolean insert(UserSql user) {
		String sql = "INSERT INTO user (email, password,type) VALUES (?,?,?)";
		return (this.db.getJdbcTemplate().update(sql,user.getEmail(),user.getPassword(), user.getUserType().getTypeOrdinal()) == 1);
	}
	
	public boolean updatePassword(UserSql currentUser, String newPassword) {
        String sql = "UPDATE user SET password=? WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, newPassword, currentUser.getEmail()) == 1);
	}
	
	public boolean updateEmail(UserSql currentUser, String newEmail) {
        String sql = "UPDATE user SET email=? WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, newEmail, currentUser.getEmail()) == 1);
	}

	public boolean updateUserType(UserSql currentUser, UserType newType) {
		String sql = "UPDATE user SET type=? WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, newType.getTypeOrdinal(), currentUser.getEmail()) == 1);
	}
	
	public boolean deleteUser(UserSql user) {
        String sql = "DELETE FROM user WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, user.getEmail()) == 1);
	}
	
	//duplicated code
	public UserType getUserType(UserSql user) {
		String sql = "Select * from user where user.email=\"" + user.getEmail() + "\"";
		return executeQuery(sql).get(0).getUserType();
	}
	
	public UserSql getUser(String email) {
		String sql = "Select * from user where user.email=\"" + email+ "\"";
		return executeQuery(sql).get(0);
	}
}
