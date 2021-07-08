package core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HashCodeTester {

	@Test
	public void test1() {
		String a = new String("ABC");
		String b = new String("ABC");
		System.out.println(a.hashCode()+"   "+b.hashCode());
		assertTrue(a.hashCode()==b.hashCode());
		assertFalse(a==b);
	}
	
}
