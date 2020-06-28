package es.restaurant.EatApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.restaurant.EatApp.models.repositories.RepositoriesTest;
import es.restaurant.EatApp.generalControllers.GeneralControllersTest;

@RunWith(Suite.class)
@SuiteClasses({
	GeneralControllersTest.class,
	RepositoriesTest.class
})
public class EatAppApplicationTests {

}
