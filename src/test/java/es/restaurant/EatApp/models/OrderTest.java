package es.restaurant.EatApp.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.restaurant.EatApp.models.ProductState.productState;
import es.restaurant.EatApp.repositories.ProductDao;

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
	
	@Test
	public void createEmptyOrder() throws Exception {
		Order order = new OrderBuilder().id(0).build();
		assertFalse("Error, empty order does not have base products!", order.getProducts().isEmpty());
		assertEquals("Error, empty order does not have all base products!", order.getProducts().size(), ProductDao.getProductDao().getProducts().size());
		assertTrue("Error, empty order has parameters!", order.getParameters().isEmpty());
		assertEquals("Error, empty order does not have userId 0", order.getUserId(),0);
		assertEquals("Error, empty order does not have Id 0", order.getId(), 0);
		assertEquals("Error, empty order does not have state BASE", order.getState().getTypeOrdinal(), OrderState.orderState.BASE.ordinal());
	}

	@Test
	public void mergeOrders() throws Exception {
		Order baseOrder = new Order();
		Order otherOrder = new OrderBuilder().baseOrder().state(new OrderState(OrderState.orderState.COOKING)).parameters("test").userId(2).build();
		baseOrder.mergeOrder(otherOrder);
		assertEquals("Error, Merge Orders fails in id", baseOrder.getId(), otherOrder.getId());
		assertEquals("Error, Merge Orders fails in parameters", baseOrder.getParameters(), otherOrder.getParameters());
		assertEquals("Error, Merge Orders fails in state", baseOrder.getState(), otherOrder.getState());
		assertEquals("Error, Merge Orders fails in userId", baseOrder.getUserId(), otherOrder.getUserId());
		assertEquals("Error, Merged Order does not have all base products!", baseOrder.getProducts().size(), ProductDao.getProductDao().getProducts().size());
		for(Entry<Product, Integer> product : otherOrder.getProducts().entrySet()) {
			for(Entry<Product, Integer>checkProduct : baseOrder.getProducts().entrySet()) {
				if(checkProduct.getKey().equals(product.getKey())) {
					checkProduct.setValue(product.getValue());
					assertTrue("Error, mergedOrder does not have the value it should have in product " + checkProduct.getKey().getName(), checkProduct.getValue() == product.getValue());
				}
			}
		}
	}

}
