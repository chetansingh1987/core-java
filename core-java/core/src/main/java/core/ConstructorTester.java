package core;

public class ConstructorTester {
    public static void main(String[] args) {
        new C1();
        new C1(1);
    }
}

class B1 {

    static
    {
        System.out.println("p");
    }


    {
        System.out.println("q");
    }

    B1(){
        System.out.println("B1 Constructor");
    }
}

class C1 extends B1 {

    //Static Block
    static {
        System.out.println("1");
    }

    //Init Block
    {
        System.out.println("2");
    }

    //Init Block
    {
        System.out.println("3");
    }

    //Static Block
    static {
        System.out.println("4");
    }

    C1(){
        System.out.println("5");
    }


    C1(int x) {
        System.out.println("6");
    }

}