package es.restaurant.EatApp.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.Product;
import es.restaurant.EatApp.models.ProductBuilder;
import es.restaurant.EatApp.models.ProductPriority;

@RunWith(SpringRunner.class)
public class ProductsDaoTest {
	
	@Test
	public void selectProductTest() {
		ProductDao dao = ProductDao.getProductDao();
		assertFalse("Error, missing base product", dao.getProducts().isEmpty());
		Product product = new ProductBuilder().baseProduct().build();
		assertTrue("Error, base product not found",dao.findProduct(product).equals(product));
	}

	@Test
	public void modifyProductTest() {
		Product product = new ProductBuilder().build();
		ProductDao dao = ProductDao.getProductDao();
		Product updatedProduct = new ProductBuilder()
				.name("newName")
				.price(10)
				.description("newDescription").priority(new ProductPriority(ProductPriority.productPriority.DESSERT)).build();
		//insert
		assertTrue("Error, cannot insert a new Product", dao.insert(product));
		//modify
		assertTrue("Error updating name", dao.updateName(product, updatedProduct.getName()));
		assertTrue("Error updating description", dao.updateDescription(product,updatedProduct.getDescription()));
		assertTrue("Error updating price", dao.updatePrice(product, updatedProduct.getPrice()));
		assertTrue("Error updating productPriority", dao.updatePriority(product, updatedProduct.getPriority()));
		//remove
		assertTrue("Error, unable to delete the ingredient", dao.deleteProduct(updatedProduct));
	}
}
