package core;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListTest {


	@Test
	public void test3() {
		List<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		List<Integer> m2 = null;
		Optional.ofNullable(m2).ifPresent(m1::addAll);
		assertEquals(m1.size(), 2);
		char a = 'a';
		char b = 'b';
		System.out.println(a+b);
	}

	@Test
	public void test1() {
		List<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		m1.add(22);
		m1.remove(1);
		System.out.println(m1.get(1));
		
	}

	@Test
	public void test4() {
		List<Integer> m1 = new ArrayList<>();
		for (Integer integer : m1) {
			System.out.println(integer);
		}


	}
	
	@Test
	public void test2() {
		List<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		List<Integer> m2 = null;
		Optional.ofNullable(m2).ifPresent(m1::addAll);
		assertEquals(m1.size(), 2);
	}
}
