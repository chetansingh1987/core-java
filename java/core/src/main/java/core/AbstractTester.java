package core;

public class AbstractTester {
public static void main(String[] args) {
	Car c = new Honda("h");
}
}

abstract class Car {
	
	String name;
	Car(String name) {
		this.name=name;
	}
	
	abstract String getName();
}

class Honda extends Car {

	Honda h = new Honda("Honda..");
	Honda(String name) {
		super("Honda");
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
}