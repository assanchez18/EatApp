package es.restaurant.EatApp.repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.models.UserType;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
public class UserDaoTest {
	    
    @Test
    public void selectUserTest() {
    	UserDao dao = UserDao.getUserDao();
    	String condition = dao.selectAllFromUser() + dao.where("id BETWEEN 1" + dao.and(" 2 ORDER BY id"));
    	List<User> listUser = dao.executeQuery(condition);
        User sergio = new UserBuilder().sergio().build();
        User admin = new UserBuilder().admin().build();
        
        assertTrue("Error, missing one of the base users", listUser.size() == 2);
        assertTrue("Error, admin user is not correct", admin.equals(listUser.get(0)));
        assertTrue("Error, sergio user is not correct", sergio.equals(listUser.get(1)));
        
    }

    @Test
    public void selectWrongUserTest() {
    	UserDao dao = UserDao.getUserDao();
    	String wrongEmail = "wrong";
        assertTrue("Error, unexisting user found in db", dao.getUser(wrongEmail) == null);        
    }

    @Test
    public void selectAllEmployeesButOneTest() {
    	EmployeeDao dao = EmployeeDao.getEmployeeDao();
    	User user = new UserBuilder().admin().build();
    	List<User> listUser = dao.getAllEmployeesBut(user.getEmail());
        assertFalse("Error, query is not working", listUser.isEmpty());
        for(User u : listUser) {
        	assertFalse("Error, the user to be excluded is in the list", u.equals(user));
        }
    }

    @Test
    public void firstInsertThenUpdatePasswordThenUpdateEmailFinallyRemoveUserTest(){
    	User user = new UserBuilder().email("test@test.com").password("1234").type(UserType.userType.COMMENSAL).build();
    	UserDao dao = UserDao.getUserDao();
    	
    	//Insert new user
    	assertTrue("Error at INSERT query", dao.insertNewUser(user));
    	assertTrue("The user has not been inserted properly, does not exist in DB",dao.isUserCorrect(user));
    	
    	User updatedUser = new UserBuilder().email("test2@test.com").password("4321").type(UserType.userType.WAITER).build();
    	//Update password
    	assertTrue("Error at UPDATE password query", dao.updatePassword(user, updatedUser.getPassword()));
    	assertFalse("The user password has not been updated properly, old data exists in DB",dao.isUserCorrect(user));
    	assertTrue("The user password has not been updated properly, does not exist in DB",dao.isUserCorrect(new User(user.getEmail(),updatedUser.getPassword())));

    	//Update userType
    	assertTrue("Error at UPDATE User type query", dao.updateUserType(user, updatedUser.getUserType()));
    	assertTrue("The user type has not been updated properly, the current type does not match with the new one",
    			   dao.getUser(user.getEmail()).equals(new User(user.getId(), user.getEmail(), updatedUser.getPassword(), updatedUser.getUserType())));

    	//Update email
    	assertTrue("Error at UPDATE email query", dao.updateEmail(user, updatedUser.getEmail()));
    	assertFalse("The user email has not been updated properly, old data exists in DB",dao.getUser(user.getEmail()) != null);
    	assertTrue("The user email has not been updated properly, does not exist in DB",dao.getUser(updatedUser.getEmail()).equals(updatedUser));

    	//Remove user
    	assertTrue("Error at DELETE  user query", dao.deleteUser(updatedUser));
    	assertFalse("The user has not been delete properly, old data exists in DB",dao.getUser(updatedUser.getEmail()) != null);
    }
}