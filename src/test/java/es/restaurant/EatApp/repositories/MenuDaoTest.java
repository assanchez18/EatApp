package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.Menu;
import es.restaurant.EatApp.models.MenuBuilder;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.models.Table;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.repositories.MenuDao;

@RunWith(SpringRunner.class)
public class MenuDaoTest {

	@Test
	public void selectMenuTest() {
		MenuDao dao = MenuDao.getMenuDao();
		Menu testMenu = new MenuBuilder().testMenu().build();
		Long id = (long) 1;
		Menu menuDB = dao.getMenu(id);

		assertTrue("Error, missing base menu", menuDB != null);
		assertTrue("Error, menu is not correct", testMenu.equals(menuDB));
	}
	
}
