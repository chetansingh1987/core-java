package core;

import org.junit.Test;

public class OOPSTesting {

	@Test
	public void test() {
		A x = new AI();
		method(x);
	}
	
	public void method(B b) {
		b.test("ABC");
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