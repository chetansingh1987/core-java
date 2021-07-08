package core;

public class InterfaceTester {

}

interface I1 {
	public void add();
}

interface I2 {
	public void add2();
}

interface S1 extends I1,I2 {
	
}

class S1Impl  implements S1 {

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add2() {
		// TODO Auto-generated method stub
		
	}
	
}