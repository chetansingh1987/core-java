package core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

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
	
	@Test
	public void test2() {
		Map<String,Integer> m = new  HashMap<>();
		m.put("A",new Integer(150));
		Integer x = m.get("A");
		++x;
		System.out.println("x="+x);
		System.out.println("m.get(\"A\")="+m.get("A"));
		assertFalse(3==m.get("A"));
		
	}
	
	@Test
	public void test3() {
		Integer i1 = 128;new Integer(128);
		Integer i2 =  new Integer(128);
		assertFalse(i1==i2);
		i1 =  new Integer(12);
		i2 = new Integer(12);
		assertFalse(i1==i2);
	}
	
	@Test
	public void test4() {
		Integer x = 3;
		Integer y = x;
		++x;
		System.out.println(x==y);
		
	}
}
