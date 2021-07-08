package core;

import org.junit.Test;

public class CloneTester {

    @Test
    public void test() throws CloneNotSupportedException {
        ABC1 x = new ABC1();
        ABC1 y = x.clone();
    }
}

class ABC1 {
    @Override
    public ABC1 clone() throws CloneNotSupportedException {
        return new ABC1();
    }
}
