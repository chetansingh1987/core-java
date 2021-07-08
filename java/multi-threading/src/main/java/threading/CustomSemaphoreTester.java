package threading;

public class CustomSemaphoreTester {

	public static void main(String[] args) throws InterruptedException {
		new CustomSemaphoreTester().test();
	}
	public void test() throws InterruptedException {
		MySemaphore m = new MySemaphore(1);
		m.aquire();m.release();
	}
}


class MySemaphore {
	
	int  permits;
	int grantedPermit =0 ;
	
	MySemaphore(int permits ) {
		this.permits=permits;
	}
	
	public void aquire() throws InterruptedException {
		synchronized (this) {
			while(grantedPermit==permits) {
				this.wait();
			}
			if(grantedPermit<permits) {
				grantedPermit++;
				this.notifyAll();
			}
		}
		
	}
	
	public void  release() throws InterruptedException  {
		synchronized (this) {
			while(grantedPermit<0) {
				this.wait();
			}
			grantedPermit--;
			this.notifyAll();
		}
	}
}