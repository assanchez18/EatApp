package es.restaurant.EatApp;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;


import es.restaurant.EatApp.GeneralControllers.LoginController;
import es.restaurant.EatApp.Models.User;
import es.restaurant.EatApp.Models.UserBuilder;
import es.restaurant.EatApp.Models.Repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoginTest {

    @Autowired
    private UserRepository userRepo;
    
	private LoginController controller;
	private User user;
	
	@Before
	public void setup() {
		this.user = new UserBuilder().sergio().build();
		userRepo.save(user);
		this.controller = new LoginController(userRepo);
	}
	
    @Test
    public void existingUserLoginSuccessfull() throws Exception {
        Model m = new ExtendedModelMap();
        
        m.addAttribute("email",this.user.getEmail());
        m.addAttribute("password",this.user.getPassword());
        
        assertEquals("Error while login.","login", this.controller.control(m, user.getEmail(), user.getPassword()));
    }
	
    @Test
    public void notExistingUserLoginError() throws Exception {
    	User u = new UserBuilder().build();
        Model m = new ExtendedModelMap();
               
        m.addAttribute("email", u.getEmail());
        m.addAttribute("password", u.getPassword());
        
        assertEquals("Error while login, the user does not exist, should not be able to login.",
        		 	 "error", this.controller.control(m, u.getEmail(), u.getPassword()));
    }	

}
