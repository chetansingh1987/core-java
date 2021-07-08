package practical;

import java.util.stream.IntStream;

public class NumberPrinter {

	public static int NO_THREADS = 3;
	public static int MAX_NUMBER = 10;
	public static void main(String[] args) throws InterruptedException {
		Signal s1 = new Signal();
		IntStream.range(0, MAX_NUMBER).forEach(x->new Thread(new Printer(s1,x)).start());
	}
}

class Printer implements Runnable {
	Signal signal ;
	int assignedTurn;
	
	public Printer(Signal signal,int assignedTurn) {
		super();
		this.signal = signal;
		this.assignedTurn=assignedTurn;
	}

	public void run() {
		synchronized(signal) {

			while(!signal.terminate) {
				while(signal.turnIndex!=assignedTurn && !signal.terminate) {
					try {
						signal.wait();
					} catch (InterruptedException e) {e.printStackTrace();}
				}
				if(signal.terminate) {
					break;
				}
				System.out.println(++signal.i);
				signal.passTurn();
				signal.notifyAll();
			}
			
		}


	}
	
}

class Signal{
	
	public int i=-1;
	public int turnIndex=0;
	public boolean terminate=false;
	public void passTurn() {
		turnIndex = turnIndex<NumberPrinter.NO_THREADS-1? ++turnIndex: 0;
		if(i==NumberPrinter.MAX_NUMBER) {
			terminate=true;
		}
	}

}