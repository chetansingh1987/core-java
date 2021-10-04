package threading;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTester {


    @Test
    public void test(){
        AtomicStampedReference<String>  asr = new AtomicStampedReference<>("chetan",1);
        boolean a = asr.compareAndSet(new String("chetan"),"Singh",1,2);
        Assert.assertFalse(a);
    }

    @Test
    public void test2(){
        AtomicStampedReference<String>  asr = new AtomicStampedReference<>("chetan",1);
        boolean a = asr.compareAndSet("chetan","Singh",1,2);
        Assert.assertTrue(a);
    }


    @Test
    public void test3(){
        AtomicStampedReference<String>  asr = new AtomicStampedReference<>("chetan",1);
        boolean a = asr.compareAndSet("chetan","Singh",1,2);
        int[] x = new int[1];
        String s = asr.get(x);
        System.out.println(x[0]);

    }
}
