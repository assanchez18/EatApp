package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserType;

public class UserDao extends Dao {
	
	private static UserDao dao;

	private static final String TABLE_NAME = "user";
	public static UserDao getUserDao() {
		if(dao == null) {
			dao = new UserDao();
		}
		return dao;
	}
	
	protected UserDao() {	
	}

	public boolean isUserCorrect(User user) {
		String sql = selectAllFrom(TABLE_NAME) + where("user.email=\""+ user.getEmail()+ "\"") + and("user.password=\"" + user.getPassword() + "\"");
		return !executeQuery(sql).isEmpty();
	}

	public String selectAllFromUser() {
		return selectAllFrom(TABLE_NAME);
	}
	
	public User getUser(User user) {
		return getUser(user.getEmail());
	}
	public User getUser(String email) {
		List<User> u = executeQuery(getUserByEmailQuery(email));
		if(u.size() == 0)
			return null;
		else
			return u.get(0);
	}
	
	private String getUserByEmailQuery(String email) {
		return selectAllFromUser() + where("user.email=\"" + email + "\"");
	}
	
	private RowMapper<User> buildUser() {
		return new RowMapper<User>() {
			public User mapRow(ResultSet result, int rowNum) throws SQLException {
				User user = new User(result.getInt("id"),result.getString("email"),result.getString("password"), result.getInt("type"));
				return user;
        	}
		};
	}
	
	public List<User> executeQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildUser());
	}
	
	public boolean insert(User user) {
		String sql = insertInto(TABLE_NAME, "(email, password,type) VALUES (?,?,?)");
		return (this.db.getJdbcTemplate().update(sql,user.getEmail(),user.getPassword(), user.getUserType().getTypeOrdinal()) == 1);
	}
	
	public boolean updatePassword(User currentUser, String newPassword) {
        String sql = update(TABLE_NAME, "password=?" + where("email=?"));
        return (this.db.getJdbcTemplate().update(sql, newPassword, currentUser.getEmail()) == 1);
	}
	
	public boolean updateEmail(User currentUser, String newEmail) {
        String sql = update(TABLE_NAME, "email=?" + where("email=?"));
        return (this.db.getJdbcTemplate().update(sql, newEmail, currentUser.getEmail()) == 1);
	}

	public boolean updateUserType(User currentUser, UserType newType) {
		String sql = update(TABLE_NAME, "type=?" + where("email=?"));
        return (this.db.getJdbcTemplate().update(sql, newType.getTypeOrdinal(), currentUser.getEmail()) == 1);
	}
	
	public boolean deleteUser(User user) {
        String sql = delete(TABLE_NAME, where("email=?"));
        return (this.db.getJdbcTemplate().update(sql, user.getEmail()) == 1);
	}

	public List<User> getAllEmployeesBut(String email) {
		String sql = selectAllFrom(TABLE_NAME) + where("(user.type = " + UserType.userType.ADMIN.ordinal()
														+ or("user.type = " + UserType.userType.WAITER.ordinal()) 
														+ or("user.type = " + UserType.userType.COOK.ordinal() + ")")
														+ and("user.email != \"" + email + "\""));
		return executeQuery(sql);
	}
}
