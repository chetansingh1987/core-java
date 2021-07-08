package core;

import java.io.*;

public class SingletonPattern {

}

//Method 1
class LazySingleton implements Serializable {
    private static final long serialVersionUID = 1L;
    private static volatile LazySingleton instance;
    private LazySingleton(){ }

    public static LazySingleton getInstance(){
        if(null==instance)
        {
            synchronized (LazySingleton.class){
                if(null==instance) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    private int i = 10;
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    protected Object readResolve(){
        return instance;
    }

}
//Method 2
enum EnumSingleton {
    INSTANCE;
    public void someMethod(String param) {
        // some class member
    }
}

//Method 3
 class BillPughSingleton {

    private BillPughSingleton() {
        System.out.println("creating...");
    }
 
    private static class LazyHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
 
    public static BillPughSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        BillPughSingleton.getInstance();
        BillPughSingleton.getInstance();
    }
}
//Serialization Safe

 class SerializationTest {
    static LazySingleton instanceOne = LazySingleton.getInstance();

    public static void main(String[] args) {
        try {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instanceOne);
            out.close();

            instanceOne.setI(20);

            // Serialize to a file
            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            instanceOne.setI(30);
            LazySingleton instanceTwo = (LazySingleton) in.readObject();
            in.close();

            System.out.println(instanceOne.getI());
            System.out.println(instanceTwo.getI());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}