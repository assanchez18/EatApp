package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.Menu;
import es.restaurant.EatApp.models.MenuBuilder;
import es.restaurant.EatApp.repositories.MenuDao;

@RunWith(SpringRunner.class)
public class MenuDaoTest {

	@Test
	public void selectMenuTest() {
		MenuDao dao = MenuDao.getMenuDao();
		Menu testMenu = new MenuBuilder().testMenu().build();
		Long id = 1L;
		Menu menuDB = dao.getMenu(id);

		assertNotNull("Error, missing base menu", menuDB);
		assertTrue("Error, menu is not correct", testMenu.equals(menuDB));
	}
	
}
