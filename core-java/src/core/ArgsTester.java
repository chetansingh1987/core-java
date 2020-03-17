package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgsTester {

    public void test1() {
        List<String> a = new ArrayList<String>();
        ArgsTester.method1(a.toArray(new String[a.size()]));
    }

    private static boolean method1(String ... args){
            return true;
    }
}
