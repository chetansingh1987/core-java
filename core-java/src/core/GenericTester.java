package core;

import org.junit.Test;

public class GenericTester {

	@Test
	public void test1() {
		ABC<R> o = new ABC<>();
		o.setValue(new R());
		R x = o.getValue();
		
		//Type Erasure at runtime equivalient code would be
		/**
		ABC o = new ABC();
		o.setValue(new R());
		R x = o.getValue();
		
		
		class ABC {
		Object x ;
	
		public void setValue(Object y) {
			x=y;
		}
		public Object getValue() {
			return x;
		}
}
		
		
		**/
		
	}
	
	
}
class R {}
class S {}

class ABC<T> {
	T x ;
	
	public void setValue(T y) {
		x=y;
	}
	public T getValue() {
		return x;
	}
}