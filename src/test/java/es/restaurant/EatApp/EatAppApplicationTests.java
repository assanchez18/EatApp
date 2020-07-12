package es.restaurant.EatApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.restaurant.EatApp.models.repositories.DaosTest;
import es.restaurant.EatApp.generalControllers.GeneralControllersTest;

@RunWith(Suite.class)
@SuiteClasses({
	GeneralControllersTest.class,
	DaosTest.class
})
public class EatAppApplicationTests {

}
