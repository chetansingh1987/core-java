package core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OOPSTesting {
	
	@Test
	public void test2() {
		BI x = new BI();
		assertEquals(1,x.meth(2));
	}

	@Test
	public void test() {
		A x = new AI();
		method(x);
	}
	
	public void method(A b) {
		b.test("ABC");
	}
}
class BI {
	public int meth(Object o) {
		return 0;
	}
	public int meth(int o) {
		return 1;
	}
}
class AI implements A {

	@Override
	public void test(String s) {
		System.out.println("AI::A "+s);
		
	}
	
}
interface A {
	void test(String s);
}
interface B {
	void test(String s);
}