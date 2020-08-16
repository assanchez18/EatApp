package es.restaurant.EatApp.models;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.ProductState.productState;

@RunWith(SpringRunner.class)
public class OrderTest {

	@Test
	public void givenTwoProductsWithQueuedStateWhenUpdatingOrderStateThenOrderStateIsQueued() throws Exception {
		ProductState stateQueued = new ProductState(productState.QUEUED);
		Product product1 = new ProductBuilder().baseProduct().state(stateQueued).build();
		Product product2 = new ProductBuilder().baseProduct().state(stateQueued).build();
		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(product1, 1);
		products.put(product2, 2);
		Order order = new OrderBuilder().baseOrder().products(products).build();
		order.updateState();
		assertTrue("Order state should be QUEUED", order.getState().isQueued());
	}
	
	@Test
	public void givenTwoProductsWithServedStateWhenUpdatingOrderStateThenOrderStateIsFinished() throws Exception {
		ProductState stateServed = new ProductState(productState.SERVED);
		Product product1 = new ProductBuilder().baseProduct().state(stateServed).build();
		Product product2 = new ProductBuilder().baseProduct().state(stateServed).build();
		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(product1, 1);
		products.put(product2, 2);
		Order order = new OrderBuilder().baseOrder().products(products).build();
		order.updateState();
		assertTrue("Order state should be FINISHED", order.getState().isFinished());
	}
	
	@Test
	public void givenTwoProductsWithCancelledStateWhenUpdatingOrderStateThenOrderStateIsCancelled() throws Exception {
		ProductState stateCancelled = new ProductState(productState.CANCELLED);
		Product product1 = new ProductBuilder().baseProduct().state(stateCancelled).build();
		Product product2 = new ProductBuilder().baseProduct().state(stateCancelled).build();
		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(product1, 1);
		products.put(product2, 2);
		Order order = new OrderBuilder().baseOrder().products(products).build();
		order.updateState();
		assertTrue("Order state should be CANCELLED", order.getState().isCancelled());
	}
	
	@Test
	public void givenOneProductWithCookingStateAndAnotherQueuedWhenUpdatingOrderStateThenOrderStateIsCooking() throws Exception {
		ProductState stateCooking = new ProductState(productState.COOKING);
		ProductState stateQueued = new ProductState(productState.CANCELLED);
		Product product1 = new ProductBuilder().baseProduct().state(stateCooking).build();
		Product product2 = new ProductBuilder().baseProduct().state(stateQueued).build();
		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(product1, 1);
		products.put(product2, 2);
		Order order = new OrderBuilder().baseOrder().products(products).build();
		order.updateState();
		assertTrue("Order state should be COOKING", order.getState().isCooking());
	}
	
	@Test
	public void givenOneProductWithCookingStateAndAnotherServedWhenUpdatingOrderStateThenOrderStateIsCooking() throws Exception {
		ProductState stateCooking = new ProductState(productState.COOKING);
		ProductState stateServed = new ProductState(productState.SERVED);
		Product product1 = new ProductBuilder().baseProduct().state(stateCooking).build();
		Product product2 = new ProductBuilder().baseProduct().state(stateServed).build();
		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(product1, 1);
		products.put(product2, 2);
		Order order = new OrderBuilder().baseOrder().products(products).build();
		order.updateState();
		assertTrue("Order state should be COOKING", order.getState().isCooking());
	}
	
	@Test
	public void givenOneProductWithReadyStateAndAnotherCancelledWhenUpdatingOrderStateThenOrderStateIsReady() throws Exception {
		ProductState stateReady = new ProductState(productState.READY);
		ProductState stateCancelled = new ProductState(productState.CANCELLED);
		Product product1 = new ProductBuilder().baseProduct().state(stateReady).build();
		Product product2 = new ProductBuilder().baseProduct().state(stateCancelled).build();
		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(product1, 1);
		products.put(product2, 2);
		Order order = new OrderBuilder().baseOrder().products(products).build();
		order.updateState();
		assertTrue("Order state should be READY", order.getState().isReady());
	}

}
