package core.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

import org.junit.Test;

public class ReenterantTester {

	public static void main(String[] args) {
		new ReenterantTester().test1();
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