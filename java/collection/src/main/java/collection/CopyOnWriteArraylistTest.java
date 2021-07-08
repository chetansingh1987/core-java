package collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class CopyOnWriteArraylistTest {

    @Test
   public void concurrencyTest(){
       MyCache c = new MyCache();
       c.addData(Arrays.asList("A"));
       new Thread(()->{
           for(int i=0;i<10000;i++)
           c.addData(Arrays.asList(i+""));
       }).start();

       new Thread(()->{
           while(true) {
               Iterator<String> iterator = c.getIterator();
               while (iterator.hasNext()) {
                   System.out.println(iterator.next());
               }
           }
       }).start();
   }

}

class MyCache {
    CopyOnWriteArrayList<String> cal = new CopyOnWriteArrayList<>();

    public void addData(List<String> data){
        cal.addAll(data);
    }

    public Iterator<String> getIterator(){
        return cal.iterator();
    }
}
