package es.restaurant.EatApp.models;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommensalTest {

	@Test
	void getCommensalIdIs1() {
		Commensal c = new Commensal();
		assertTrue(c.getId() == 1, "User ID is not 1");
	}
	
}
