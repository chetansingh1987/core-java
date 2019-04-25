package core;

import java.util.ArrayList;

import org.junit.Test;

public class ArrayListTester {

	@Test
	public void test1() {
		ArrayList<String> l = new ArrayList<>();
		l.add("A");
		l.add("B");
		l.remove("A");
		System.out.println(l.get(0));
		System.out.println("A     B".split(" ")[1]);
	}
	
	public static void main(String[] args) {
		
	}
}
