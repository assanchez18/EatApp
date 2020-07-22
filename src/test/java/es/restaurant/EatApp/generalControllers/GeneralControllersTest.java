package es.restaurant.EatApp.generalControllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AskForHelpTest.class,
	StartTest.class,
	LoginTest.class,
	LogoutTest.class,
	RegisterInTableTest.class
})
public class GeneralControllersTest {

}
