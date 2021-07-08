package core;

import static org.junit.Assert.assertTrue;

import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTester {
	
	@Test
	public void test1() {
		TreeMap<TMA,String> tm = new TreeMap<>();
		TMA a1 = new TMA("ABC");
		TMA a2 = new TMA("ABC");
		TMA a3 = new TMA("ABCD");
		tm.put(a1,"a1");
		tm.put(a2,"a1a2");
		tm.put(a3,"a3");
		assertTrue(tm.size()==2);
		assertTrue(tm.get(a1).equalsIgnoreCase("a1a2"));
	}

}

class TMA implements Comparable<TMA>{
	String label;

	public TMA(String label) {
		this.label = label;
	}

	@Override
	public int compareTo(TMA o) {
		return label.compareTo(o.label);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
