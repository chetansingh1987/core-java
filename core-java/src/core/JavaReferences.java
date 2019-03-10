package core;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import org.junit.Test;

public class JavaReferences {
	
	public void testSoft() {
		Foo f = new Foo();
		SoftReference<Foo> soft = new SoftReference<Foo>(f);
		f=null;
		soft.get();
	}
	
	public void testWeak() {
		Foo f = new Foo();
		WeakReference<Foo> soft = new WeakReference<Foo>(f);
		f=null;
		soft.get();
	
	}
	@Test
	public void testPhantom() throws InterruptedException {
		ReferenceQueue<Foo> rqueue = new ReferenceQueue<>();
		Foo f = new Foo();
		PhantomReference<Foo> phantomRef = new PhantomReference<Foo>(f, rqueue);
		f=null;
		System.gc();
		Thread.sleep(5*1000);
		rqueue.remove();
		System.out.println("no do clearup things");
		
	}
}

class Foo{

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Foo Finalized");
		super.finalize();
	}
	
	
}
