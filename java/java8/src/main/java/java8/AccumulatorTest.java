package java8;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import org.junit.Test;

public class AccumulatorTest {

	public static void main(String[] args) {

	}
	@Test
	public void test1() {
		int nT=1;
		int maxNumber=3;
		CountDownLatch cdL = new CountDownLatch(maxNumber);
		ExecutorService fixedPool = Executors.newFixedThreadPool(nT);
	    //LongAccumulator adder  =  new LongAccumulator((x,y)->x+y,0l);
		LongAdder adder = new LongAdder();
	    Runnable runable = ()->{
	    						 System.out.println(this.hashCode());
	    						 adder.increment();
	    	                     //adder.accumulate(1);
	    	                     cdL.countDown();};
	    IntStream.range(0, maxNumber).forEach(i->{
	    	fixedPool.submit(runable);
	    });
	    try {
			cdL.await();
			fixedPool.shutdown();
			assertEquals(maxNumber,adder.sum());
			//assertEquals(maxNumber,adder.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
