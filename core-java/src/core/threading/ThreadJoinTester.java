package core.threading;

public class ThreadJoinTester {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new A());
		t1.join();
		Thread.sleep(5*1000);
		t1.start();
		System.out.println("Main thread ending");
	}
}

class A implements Runnable{

	@Override
	public void run() {
		Thread t2= new Thread(new B());
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
		System.out.println("A thread ending");
	}

}

class B implements Runnable{

	@Override
	public void run() {
		System.out.println("B thread ending");
	}

}