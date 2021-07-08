package threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;
import java.util.stream.IntStream;

import org.junit.Test;

public class ReenterantTester {

	public static void main(String[] args) throws InterruptedException {
		new ReenterantTester().test3();
	}
	
	@Test
	public void test1() {
		ExecutorService service = Executors.newFixedThreadPool(5);
		Lock lock = new ReentrantLock();
		IntStream.range(0, 5).forEach(x->{
			service.submit(new ELObj(lock,x+""));
		});
		service.shutdown();
	}

	@Test
	public void test3() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(5);
		Lock lock = new ReentrantLock();
		Condition test = lock.newCondition();
		IntStream.range(0, 5).forEach(x->{
			service.submit(new ConditionTest(lock,x+"",test));
		});
		Thread.sleep(1000);
		IntStream.range(0, 5).forEach(x->{
			try {
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			;
			 test.signal();
			lock.unlock();
		});
		service.shutdown();
	}

	@Test
	public void test2() {
		ExecutorService service = Executors.newFixedThreadPool(5);
		ReadWriteLock lock = new ReentrantReadWriteLock();
		IntStream.range(0, 5).forEach(x->{
			service.submit(new RRWLObj(lock,x+""));
		});
		service.shutdown();
	}
	
}
class RRWLObj implements Runnable {
	
	ReadWriteLock lock;
	String name;

	public RRWLObj(ReadWriteLock lock,String name) {
		super();
		this.lock = lock;
		this.name=name;
	}


	@Override
	public void run() {
		lock.readLock();
		System.out.println("---" +name+"-Start--" );
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---" +name+"-End--" );
		lock.readLock().unlock();
	}
	
}
class ELObj implements Runnable {
	
	Lock lock;
	String name;

	public ELObj(Lock lock,String name) {
		super();
		this.lock = lock;
		this.name=name;
	}
	@Override
	public void run() {
		lock.lock();
		System.out.println("---" +name+"-Start--" );
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---" +name+"-End--" );
		lock.unlock();
	}
}

class ConditionTest implements Runnable {

	Lock lock;
	String name;
	Condition test;

	public ConditionTest(Lock lock, String name, Condition test) {
		this.lock = lock;
		this.name = name;
		this.test = test;
	}


	@Override
	public void run() {
		lock.lock();
		System.out.println("---" +name+"-Start--" );
		try {
			test.await();
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---" +name+"-End--" );
		lock.unlock();
	}
}