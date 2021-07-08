package practical;

public class EvenOddPrinter implements Runnable{

	public static void main(String[] args) {
		Printer2 printer = new Printer2();
		EvenOddPrinter ev = new  EvenOddPrinter(true, printer);
		EvenOddPrinter odd = new  EvenOddPrinter(false, printer);
		new Thread(ev).start();
		new Thread(odd).start();
	}

	boolean isEven;
	Printer2 printer;
	EvenOddPrinter(boolean isEven , Printer2 printer){
		this.isEven=isEven;
		this.printer=printer;
	}
	@Override
	public void run() {
		int number = isEven?2:1;
		while(number<=10) {
			if(isEven) {
				printer.printEven(number);
			}else {
				printer.printOdd(number);
			}
			number=number+2;
		}
	}
}

 
class Printer2 {
	
	private volatile boolean isEvenTurn = false;
	
	public synchronized void printEven(int number) {
		while(!isEvenTurn) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(number);
		isEvenTurn=false;
		notify();
	}
	
	public synchronized void printOdd(int number) {
		while(isEvenTurn) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(number);
		isEvenTurn=true;
		notify();

	}
	
}