package core;

import org.junit.Test;

import java.util.HashMap;
import java.util.WeakHashMap;

import static org.junit.Assert.assertEquals;

public class WeakHashMapTest {

	
	@Test
	public void test1() throws InterruptedException {
		 HashMap m = new HashMap(); 
	     Demo d = new Demo(); 
	     m.put(d," Hi ");  
	     System.out.println(m);
	     d = null; 
	     System.gc(); 
	     Thread.sleep(4000);  
	     assertEquals(1, m.size()); 
	}
	
	@Test
	public void test2( ) throws InterruptedException {
		WeakHashMap m = new WeakHashMap(); 
	     Demo d = new Demo(); 
	     m.put(d," Hi ");  
	     System.out.println(m);
	     d = null; 
	     System.gc(); 
	     Thread.sleep(4000); 
	     assertEquals(0, m.size()); //The Size=0 as no other reference apart from map was there at time of GC
	}
}



class Demo 
{ 
	public String toString() 
	{ 
		return "demo"; 
	} 

	// finalize method 
	public void finalize() 
	{ 
		System.out.println("finalize method is called"); 
	} 
} 
