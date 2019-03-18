package core.threading;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class ExceptionHandlingThreads {

	@Test
	public void test() {
		ExecutorService es = Executors.newFixedThreadPool(1);
		Future f = es.submit(new A1());
		try {
			f.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			System.out.println("............................"+f.isDone());
		}		
		
	}
}

class A1 implements Runnable {

	@Override
	public void run() {
		throw new ArithmeticException();
		
	}
	
}
