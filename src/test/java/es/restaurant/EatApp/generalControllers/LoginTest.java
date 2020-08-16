package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import es.restaurant.EatApp.models.Order;
import es.restaurant.EatApp.models.OrderBuilder;
import es.restaurant.EatApp.models.User;
import es.restaurant.EatApp.models.UserBuilder;
import es.restaurant.EatApp.repositories.OrderDao;
import es.restaurant.EatApp.repositories.TableDao;
import es.restaurant.EatApp.views.LoginView;
import es.restaurant.EatApp.views.View;

@RunWith(SpringRunner.class)
public class LoginTest {

	private LoginController controller;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.controller = new LoginController();
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

    @Test
    public void existingUserLoginWitOrderAndTableInCache() throws Exception {
		User user = new UserBuilder().sergio().build();
		Order order = new OrderBuilder().baseOrder().userId(user.getId()).build();
		int table = 123;
		TableDao.getTableDao().linkUserToTable(user.getId(), table);
		OrderDao.getOrderDao().saveInCache(order);
        this.mockMvc.perform(
                post("/login")
                        .param(View.TAG_EMAIL, user.getEmail())
                        .param(LoginView.TAG_PASSWORD, user.getPassword()))
                .andExpect(status().isOk());
		OrderDao.getOrderDao().deleteFromCache(user.getId());
		TableDao.getTableDao().unlinkUserToTable(user.getId());
    }

    @Test
    public void existingUserLoginWithoutOrderInCache() throws Exception {
		User user = new UserBuilder().sergio().build();
        this.mockMvc.perform(
                post("/login")
                        .param(View.TAG_EMAIL, user.getEmail())
                        .param(LoginView.TAG_PASSWORD, user.getPassword()))
                .andExpect(status().isOk());
    }

    @Test
    public void unauthorizedLoginError() throws Exception {
    	User u = new UserBuilder().sergio().build();
    	String wrongPassword = "other";

        this.mockMvc.perform(
                post("/login")
                        .param(View.TAG_EMAIL, u.getEmail())
                        .param(LoginView.TAG_PASSWORD, wrongPassword))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void userNotFoundLoginError() throws Exception {
    	User u = new UserBuilder().build();

        this.mockMvc.perform(
                post("/login")
                        .param(View.TAG_EMAIL, u.getEmail())
                        .param(LoginView.TAG_PASSWORD, u.getPassword()))
                .andExpect(status().isUnauthorized());
    }
}
