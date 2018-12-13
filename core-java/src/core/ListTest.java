package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class ListTest {

	@Test
	public void test1() {
		List<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		List<Integer> m2 = new ArrayList<>();
		m2.add(3);
		m2.add(4);
		m1.addAll(m2);
		assertEquals(m1.size(), 4);
		
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
