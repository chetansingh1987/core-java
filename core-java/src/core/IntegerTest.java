package core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IntegerTest {

	@Test
	public void test1() {
		Integer i1 = 128;
		Integer i2 = 128;
		assertFalse(i1==i2);
		
		i1 = 12;
		i2 = 12;
		assertTrue(i1==i2);
		
	}
}
