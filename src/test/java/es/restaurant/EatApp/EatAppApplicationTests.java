package es.restaurant.EatApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.restaurant.EatApp.Models.Repositories.RepositoriesTest;
import es.restaurant.EatApp.Controllers.ControllersTest;

@RunWith(Suite.class)
@SuiteClasses({
	ControllersTest.class,
	RepositoriesTest.class
})
public class EatAppApplicationTests {

}
