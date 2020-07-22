package es.restaurant.EatApp.repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	UserDaoTest.class,
	TableDaoTest.class,
	WaiterDaoTest.class,
	IngredientsDaoTest.class
})
public class DaosTest {

}
