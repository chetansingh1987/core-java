package com.cs.java.tricky;

public class ConstructorTest {

	 public static void main(String[] args) {
		new A2(Integer.MAX_VALUE);
	}
	
}

class A2 {
	A2(String x){
		System.out.println("String...");
	}
	
	A2(Object y){
		System.out.println("Object...");
	}
	
	A2(Long y){
		System.out.println("Long...");
	}
	A2(Integer y){
		System.out.println("Integer...");
	}
}