package es.restaurant.EatApp.models.repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.models.UserSql;
import es.restaurant.EatApp.models.UserType;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
public class DatabaseTest {
	    
    @Test
    public void selectUserTest() {
    	String sqlSelect = "SELECT * FROM user WHERE id BETWEEN 1 and 2 ORDER BY id";
        List<UserSql> listUser = new UserSqlDao().executeQuery(sqlSelect);
        User sergio = new UserBuilder().sergio().buildSQL();
        User admin = new UserBuilder().admin().buildSQL();
        
        assertTrue("Error, missing one of the base users", listUser.size() == 2);
        assertTrue("Error, admin user is not correct", admin.equals(listUser.get(0)));
        assertTrue("Error, sergio user is not correct", sergio.equals(listUser.get(1)));
        
    }
    
    @Test
    public void firstInsertThenUpdatePasswordThenUpdateEmailFinallyRemoveUserTest(){
    	UserSql user = new UserBuilder().email("test@test.com").password("1234").type(UserType.userType.COMMENSAL).buildSQL();
    	UserSqlDao dao = new UserSqlDao();
    	
    	//Insert new user
    	assertTrue("Error at INSERT query", dao.insert(user));
    	assertTrue("The user has not been inserted properly, does not exist in DB",dao.verifyUser(user));
    	
    	UserSql updatedUser = new UserBuilder().email("test2@test.com").password("4321").type(UserType.userType.WAITER).buildSQL();
    	//Update password
    	assertTrue("Error at UPDATE password query", dao.updatePassword(user, updatedUser.getPassword()));
    	assertFalse("The user password has not been updated properly, old data exists in DB",dao.verifyUser(user));
    	assertTrue("The user password has not been updated properly, does not exist in DB",dao.verifyUser(new UserSql(user.getEmail(),updatedUser.getPassword())));

    	//Update userType
    	assertTrue("Error at UPDATE User type query", dao.updateUserType(user, updatedUser.getUserType()));
    	assertTrue("The user type has not been updated properly, the current type does not match with the new one", dao.getUserType(user).equals(updatedUser.getUserType()));

    	//Update email
    	assertTrue("Error at UPDATE email query", dao.updateEmail(user, updatedUser.getEmail()));
    	assertFalse("The user email has not been updated properly, old data exists in DB",dao.verifyUser(user));
    	assertTrue("The user email has not been updated properly, does not exist in DB",dao.verifyUser(updatedUser));

    	//Remove user
    	assertTrue("Error at DELETE  user query", dao.deleteUser(updatedUser));
    	assertFalse("The user has not been delete properly, old data exists in DB",dao.verifyUser(updatedUser));
    }
}