package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserType;

public class UserDao {
	
	private Database db;
	private List<User> lastSelectedData;
	
	public UserDao() {
		this.db = Database.getDatabase();
		this.lastSelectedData = new ArrayList<User>();
	}

	public boolean verifyUserAndPassword(User user) {
		String sql = "Select * from user where user.email=\""+ user.getEmail()+ "\" and user.password=\"" + user.getPassword() + "\"";
		this.lastSelectedData = executeQuery(sql);
		return !this.lastSelectedData.isEmpty();
	}

	public boolean verifyUserExists(User user) {
		this.lastSelectedData = executeQuery(getUserByEmailQuery(user.getEmail()));
		return !this.lastSelectedData.isEmpty();
	}
	
	public User getUser(User user) {
		return executeQuery(getUserByEmailQuery(user.getEmail())).get(0);
	}
	public User getUser(String email) {
		return executeQuery(getUserByEmailQuery(email)).get(0);
	}
	
	private String getUserByEmailQuery(String email) {
		return "Select * from user where user.email=\"" + email + "\"";
	}
	
	private RowMapper<User> buildUser() {
		return new RowMapper<User>() {
			public User mapRow(ResultSet result, int rowNum) throws SQLException {
				User user = new User(result.getLong("id"),result.getString("email"),result.getString("password"), result.getInt("type"));
				return user;
        	}
		};
	}
	
	public User getFirstSelectedUser() {
		assert(!this.lastSelectedData.isEmpty());
		return this.lastSelectedData.get(0);
	}
	
	public List<User> executeQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildUser());
	}
	
	public boolean insert(User user) {
		String sql = "INSERT INTO user (email, password,type) VALUES (?,?,?)";
		return (this.db.getJdbcTemplate().update(sql,user.getEmail(),user.getPassword(), user.getUserType().getTypeOrdinal()) == 1);
	}
	
	public boolean updatePassword(User currentUser, String newPassword) {
        String sql = "UPDATE user SET password=? WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, newPassword, currentUser.getEmail()) == 1);
	}
	
	public boolean updateEmail(User currentUser, String newEmail) {
        String sql = "UPDATE user SET email=? WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, newEmail, currentUser.getEmail()) == 1);
	}

	public boolean updateUserType(User currentUser, UserType newType) {
		String sql = "UPDATE user SET type=? WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, newType.getTypeOrdinal(), currentUser.getEmail()) == 1);
	}
	
	public boolean deleteUser(User user) {
        String sql = "DELETE FROM user WHERE email=?";
        return (this.db.getJdbcTemplate().update(sql, user.getEmail()) == 1);
	}
}
