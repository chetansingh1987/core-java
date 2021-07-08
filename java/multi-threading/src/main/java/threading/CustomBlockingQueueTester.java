package threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Test;

public class CustomBlockingQueueTester {

	public static void main(String[] args) {
		new CustomBlockingQueueTester().test();
	}
	
	@Test
	public void test() {
		MyBlockingQueue<String> blocking = new MyBlockingQueue(1);
		
		Runnable reader = ()-> {
								try {
									  blocking.take();
							    } catch (InterruptedException e) {e.printStackTrace();}
						};
							
							
		for(int i=0;i<10;i++) {
			new Thread( reader ).start();
		}
		
		
		Runnable writer = ()->{
								try {
									  blocking.offer("A");
								} catch (InterruptedException e) { e.printStackTrace();}
							  };
		
		for(int i=0;i<=5;i++) {
			new Thread( writer ).start();
		} 
		
	}
}

class MyBlockingQueue <E> {
	
  private final List<E> list ;
  private final int  size ;
  
  public MyBlockingQueue(int size) {
	  this.list = new ArrayList<>(size) ;
	  this.size = size ;
  }
  
  public E take() throws InterruptedException {
	  E item = null;
	  synchronized(list) {
		  while(list.isEmpty()) {
			  list.wait();
		  }
		 item = list.remove(list.size()-1) ;
		 System.out.println("take called");
		 list.notifyAll();
	  }
	  
	  return item ;
  }
  
  public void offer(E item) throws InterruptedException {
	 
	  synchronized(list) {
		  while(list.size()==size) {
			  list.wait();
		  }
		 list.add(item);
		 System.out.println("offer called");
		 list.notifyAll();
	  }
  }
  
}
