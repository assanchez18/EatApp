package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


import es.restaurant.EatApp.models.Table;
import es.restaurant.EatApp.repositories.TableDao;

@RunWith(SpringRunner.class)
public class TableDaoTest {

	@Test
	public void selectTableTest() {
		TableDao dao = TableDao.getTableDao();
		int code = 123;
		List<Table> tables = dao.executeQuery(dao.selectAllTables() + " WHERE code = " + code);
		Table table = new Table(code);

		assertTrue("Error, missing base waiter", tables.size() == 1);
		assertTrue("Error, waiter user is not correct", table.equals(tables.get(0)));
	}

}
