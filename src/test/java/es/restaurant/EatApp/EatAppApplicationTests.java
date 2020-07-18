package es.restaurant.EatApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.restaurant.EatApp.generalControllers.GeneralControllersTest;
import es.restaurant.EatApp.repositories.DaosTest;

@RunWith(Suite.class)
@SuiteClasses({
	GeneralControllersTest.class,
	DaosTest.class
})
public class EatAppApplicationTests {

}
