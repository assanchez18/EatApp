package es.restaurant.EatApp.Models.Repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import es.restaurant.EatApp.Models.UserJpa;
import es.restaurant.EatApp.Models.UserBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;
    
    @Test
    public void canFindTheUserAfterSave() {
        UserJpa user = new UserBuilder().sergio().buildJPA();        
        userRepo.save(user);
        
        UserJpa found = userRepo.findUserByNameAndPassword(user.getEmail(), user.getPassword());
  
        assertNotNull("The user does not exist!",found);
        assertEquals("The email is not correct!", user.getEmail(), found.getEmail());
        assertEquals("The password is not correct!", user.getPassword(), found.getPassword());
    }
    
    @Test
    public void returnNullWhenNotFound(){
        UserJpa found = userRepo.findUserByNameAndPassword("email", "password");
        assertNull("User should not be found!", found);
    }
}