package es.restaurant.EatApp.repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	UserDaoTest.class,
	TableDaoTest.class,
	WaiterDaoTest.class,
	IngredientsDaoTest.class,
	ProductsDaoTest.class,
	MenuDaoTest.class,
	ProductsDaoTest.class
})
public class DaosTest {

}
