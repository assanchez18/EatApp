package es.restaurant.EatApp.Models.Repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	DatabaseTest.class,
	UserRepositoryTest.class
})
public class RepositoriesTest {

}
