package es.restaurant.EatApp.generalControllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AskForHelpTest.class,
	ManageQuantityOfIngredientsTest.class,
	StartTest.class,
	LoginTest.class,
	LogoutTest.class,
	RegisterInTableTest.class,
	CreateNewOrderTest.class,
	ShowOrderTest.class,
	CleanNotificationControllerTest.class,
	RegisterAndRemoveEmployeeControllerTest.class,
	ShowIngredientsAmountControllerTest.class
})
public class GeneralControllersTest {

}
