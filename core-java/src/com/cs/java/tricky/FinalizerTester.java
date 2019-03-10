package com.cs.java.tricky;

import org.junit.Test;

public class FinalizerTester {

	@Test
	public void test1() {
		A a = new A();
		a.meth();
		a=null;
		System.gc();
		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


class A {
	
	public void meth() {
		
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Calling finalize");
		throw new Error();
	}
	
	
}