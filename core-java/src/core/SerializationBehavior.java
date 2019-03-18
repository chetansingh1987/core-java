package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

public class SerializationBehavior {

	public static void main(String[] args) {
			try {
				SerializationBehavior.test1();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public static void test1() throws FileNotFoundException, IOException, ClassNotFoundException{
		Child c = new Child("Child");
		
		System.out.println("Serializing......");
		serializeObj(c,"f.ser");
		System.out.println("DeSerializing......");
		Child c2 = (Child) deSerializeObj("f.ser");
		System.out.println("Value of c2.parentField"+c2.parentField);
		System.out.println("Value of c2.childField"+c2.childField);
		
	}
	
	
	public static Object deSerializeObj(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream oos = new ObjectInputStream(new FileInputStream(new File(filename)));
		return oos.readObject();
	}
	
	public static void serializeObj(Object o,String filename) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename)));
		oos.writeObject(o);
		oos.close();
	}
}


class Parent {
	String parentField ;
	Parent(String s ){
		this.parentField=s;
		System.out.println("Parent constructor "+s);
	
	}
	
	Parent(){}
}

class Child extends Parent implements Serializable{
	String childField ;
    Child(){
    	super("parent default");
    }
	Child(String a) {
		super("parent");
		this.childField=a;
		System.out.println("Child constructor "+a);
	}
	private void readObject(ObjectInputStream ois) {
		System.out.println("readObject");
	}
}