package core.reflection;

import org.junit.Test;

import java.util.Arrays;

public class ReflectionTester {

    @Test
    public void test1() {
        B obj = new B();
        Arrays.stream(B.class.getInterfaces()).forEach(System.out::println);

        for (Class<?> intfc : obj.getClass().getInterfaces()) {
            intfc.getName();
        }
    }
}


interface  I1 {
    void m();
}

interface  I2 {
    void m2();
}
class A implements I1 {

    @Override
    public void m() {

    }
}

class B extends A implements I2,I1 {

    @Override
    public void m2() {

    }
}
