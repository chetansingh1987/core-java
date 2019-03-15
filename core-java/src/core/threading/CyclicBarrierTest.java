package core.threading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		try {
			CyclicBarrierTest.test1();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public static void test1() throws InterruptedException, BrokenBarrierException {
		CyclicBarrier cb = new CyclicBarrier(2);
		T1 t1 = new T1(cb);
		T2 t2 = new T2(cb);
		new Thread(t1).start();
		new Thread(t2).start();
	}
}

class T2 implements Runnable {
	
	CyclicBarrier cb;
	
	public T2(CyclicBarrier cb) {
		super();
		this.cb = cb;
	}
	@Override
	public void run() {
		try {
			System.out.println("T2 before barrier");
			Thread.sleep(10*1000);
			cb.await();
			
		}
		 catch (InterruptedException e) {
			 System.out.println("T2 InterruptedException");
		} catch (BrokenBarrierException e) {
			System.out.println("T2 BrokenBarrierException");
		}
		
		System.out.println("T2 after barrier");
	}
	
}

class T1 implements Runnable {

	CyclicBarrier cb;
	
	public T1(CyclicBarrier cb) {
		super();
		this.cb = cb;
	}

	@Override
	public void run() {
		System.out.println("T1 before barrier");
		try {
			cb.await(1,TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			 System.out.println("T1 InterruptedException");
		} catch (BrokenBarrierException e) {
			System.out.println("T1 BrokenBarrierException");
		}catch (TimeoutException e) {
			System.out.println("T1 TimeoutException");
		}
		System.out.println("T1 after barrier");
		
	}
	
}

