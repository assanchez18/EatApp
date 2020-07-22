package es.restaurant.EatApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import es.restaurant.EatApp.models.Menu;

public class MenuDao extends Dao{
	
	private static MenuDao dao;
	private static final String MENU_NAME = "menu";

	public static MenuDao getMenuDao() {
		if(dao == null) {
			dao = new MenuDao();
		}
		return dao;
	}
	
	private RowMapper<Menu> buildMenu() {
		return new RowMapper<Menu>() {
			public Menu mapRow(ResultSet result, int rowNum) throws SQLException {
				Menu menu = new Menu(result.getLong("id"), result.getString("description"));
				return menu;
        	}
		};
	}
	
	public List<Menu> executeQuery(String sql) {
		return this.db.getJdbcTemplate().query(sql, buildMenu());
	}
	
	public String selectAllFromMenu(String condition) {
		return selectAllFrom(MENU_NAME) + where(condition);
	}
	
	private String getMenuById(Long id) {
		return selectAllFromMenu("menu.id=\"" + id + "\"");
	}
	
	public Menu getMenu(Long id) {
		return executeQuery(getMenuById(id)).get(0);
	}

}
