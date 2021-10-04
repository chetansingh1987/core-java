package core;

public class Bar {

    public Bar() {
        System.out.println("Bar Constructor >>> " );
    }

    public void printCL() {
        System.out.println("Bar ClassLoader: "+Bar.class.getClassLoader());
    }
}
