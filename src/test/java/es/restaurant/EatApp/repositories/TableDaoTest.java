package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


import es.restaurant.EatApp.models.Table;

@RunWith(SpringRunner.class)
public class TableDaoTest {

	@Test
	public void selectTableTest() {
		TableDao dao = TableDao.getTableDao();
		int code = 123;
		String condition = "code = " + code;
		List<Table> tables = dao.executeQuery(dao.selectAllTables(condition));
		Table table = new Table(code);

		assertTrue("Error, missing base table", tables.size() == 1);
		assertTrue("Error, table is not correct", table.equals(tables.get(0)));
	}

	@Test
	public void linkUserToTableTest() {
		TableDao dao = TableDao.getTableDao();
		Table table = new Table(123);
		int userId = 1;
		dao.linkUserToTable(userId, table);
		assertTrue("Table should be 123", dao.getTableWithUserId(userId).equals(table));
	}

	@Test
	public void unlinkUserToTableTest() {
		TableDao dao = TableDao.getTableDao();
		Table table = new Table(123);
		int userId = 1;
		dao.linkUserToTable(userId, table);
		dao.unlinkUserToTable(userId);
		assertTrue("Table should be -1 (not found)", dao.getTableWithUserId(userId).equals(new Table()));
	}

}
