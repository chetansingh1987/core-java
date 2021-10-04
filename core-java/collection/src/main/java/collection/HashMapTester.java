package collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HashMapTester {

    @Test
    public void test1(){
        Map<String,Integer> m = new HashMap<>();
        m.put("d",10);
        m.put("a",4);
        m.put("b",3);
        m.put("c",1);
        String v = Collections.max(m.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        Assert.assertTrue(v.equalsIgnoreCase("d"));
    }
}
