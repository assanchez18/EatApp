package es.restaurant.EatApp.generalControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import es.restaurant.EatApp.models.Ingredient;
import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.repositories.IngredientDao;
import es.restaurant.EatApp.repositories.ProductDao;
import es.restaurant.EatApp.repositories.ProductIngredientsDao;
import es.restaurant.EatApp.views.ManageMenuView;
import es.restaurant.EatApp.views.OrderView;
import es.restaurant.EatApp.views.ProductView;

@RunWith(SpringRunner.class)
public class ManageMenuControllerTest {
	
	private ManageMenuController manageMenuController;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.manageMenuController = new ManageMenuController();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.manageMenuController).build();
		
	}

	@Test
	public void changeProductTest() throws Exception {
		
		String wrongProductId = "-1";
		Product product = new Product(-2,"product Name Test","product Description Test", 10, 0);
		Ingredient ingredientTest1 = new Ingredient(-1, "ingredient Test", "ingredient to change Test");
		Ingredient ingredientTest2 = new Ingredient(-2, "ingredient Test", "ingredient to change Test");
		//clean before to develop
		ProductIngredientsDao.getProductIngredientDao().deleteAllIngredientsFromProduct(product.getId());
		IngredientDao.getIngredientDao().deleteIngredient(ingredientTest1);
		IngredientDao.getIngredientDao().deleteIngredient(ingredientTest2);
		ProductDao.getProductDao().deleteProduct(product.getId());
		//insert data
		IngredientDao.getIngredientDao().insert(ingredientTest1);
		IngredientDao.getIngredientDao().insert(ingredientTest2);
		ProductDao.getProductDao().insertWithId(product);
		List<Integer> newIngredientsId = new ArrayList<Integer>();
		newIngredientsId.add(ingredientTest1.getId());
		ProductIngredientsDao.getProductIngredientDao().updateIngredientsInProduct(newIngredientsId, product.getId());
		
		
		this.mockMvc.perform(
				get("/manageMenu"))
				.andExpect(status().isOk());
		this.mockMvc.perform(
				post("/changeProductInMenu")
				.param(ProductView.TAG_PRODUCT_ID, wrongProductId))
				.andExpect(status().isOk());
		this.mockMvc.perform(
				post("/productChanged")
				.param(ManageMenuView.TAG_PRODUCT_ID, wrongProductId)
				.param(ManageMenuView.TAG_PRODUCT_NAME, ""))
				.andExpect(status().isBadRequest());
		this.mockMvc.perform(
				post("/productChanged")
				.param(ManageMenuView.TAG_PRODUCT_ID, wrongProductId)
				.param(ManageMenuView.TAG_PRODUCT_NAME, product.getName())
				.param(ManageMenuView.TAG_PRODUCT_DESCRIPTION, ""))
				.andExpect(status().isBadRequest());
		this.mockMvc.perform(
				post("/productChanged")
				.param(ManageMenuView.TAG_PRODUCT_ID, wrongProductId)
				.param(ManageMenuView.TAG_PRODUCT_NAME, product.getName())
				.param(ManageMenuView.TAG_PRODUCT_DESCRIPTION, product.getDescription()))
				.andExpect(status().isBadRequest());
		this.mockMvc.perform(
				post("/productChanged")
				.param(ManageMenuView.TAG_PRODUCT_ID, Integer.toString((product.getId())))
				.param(ManageMenuView.TAG_PRODUCT_NAME, product.getName())
				.param(ManageMenuView.TAG_PRODUCT_DESCRIPTION, product.getDescription())
				.param(ManageMenuView.TAG_PRODUCT_PRICE, Double.toString(product.getPrice()))
				.param(ManageMenuView.TAG_PRODUCT_PRIORITY, Integer.toString(product.getPriority().getTypeOrdinal())))
				.andExpect(status().isOk());
		//change ingredient
		this.mockMvc.perform(
				post("/newProductIngredients")
				.param(ManageMenuView.TAG_PRODUCT_ID, Integer.toString((product.getId())))
				.param(ManageMenuView.TAG_PRODUCT_NAME, product.getName())
				.param(ManageMenuView.TAG_PRODUCT_DESCRIPTION, product.getDescription())
				.param(ManageMenuView.TAG_PRODUCT_PRICE, Double.toString(product.getPrice()))
				.param(ManageMenuView.TAG_PRODUCT_PRIORITY, Integer.toString(product.getPriority().getTypeOrdinal()))
				.param(OrderView.TAG_IDS,"")
				.param(ManageMenuView.TAG_CONTAINS_INGREDIENTS,""))
				.andExpect(status().isBadRequest());
		String[] ids = {Integer.toString(ingredientTest1.getId()),Integer.toString(ingredientTest2.getId())};
		String[] newIngredients = {"0","1"};
		this.mockMvc.perform(
				post("/newProductIngredients")
				.param(ManageMenuView.TAG_PRODUCT_ID, Integer.toString((product.getId())))
				.param(ManageMenuView.TAG_PRODUCT_NAME, product.getName())
				.param(ManageMenuView.TAG_PRODUCT_DESCRIPTION, product.getDescription())
				.param(ManageMenuView.TAG_PRODUCT_PRICE, Double.toString(product.getPrice()))
				.param(ManageMenuView.TAG_PRODUCT_PRIORITY, Integer.toString(product.getPriority().getTypeOrdinal()))
				.param(OrderView.TAG_IDS,ids)
				.param(ManageMenuView.TAG_CONTAINS_INGREDIENTS,newIngredients))
				.andExpect(status().isOk());
		
		
		//clean test
		ProductIngredientsDao.getProductIngredientDao().deleteAllIngredientsFromProduct(product.getId());
		IngredientDao.getIngredientDao().deleteIngredient(ingredientTest1);
		IngredientDao.getIngredientDao().deleteIngredient(ingredientTest2);
		ProductDao.getProductDao().deleteProduct(product.getId());
		
	}

}
