package core.threading;

public class ThreadJoinTester {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new A());
		t1.join();
		t1.start();
		System.out.println("Main thread ending");
	}
}

class A implements Runnable{

	@Override
	public void run() {
		System.out.println("A thread ending");
	}

}