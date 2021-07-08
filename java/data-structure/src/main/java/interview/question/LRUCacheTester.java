package interview.question;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LRUCacheTester {
    public static void main(String[] args) {
        LRUCache ca = new LRUCache(4);
        ca.refer(1);
        ca.refer(2);
        ca.refer(3);
        ca.display();
        ca.refer(1);
        ca.display();
        ca.refer(4);
        ca.refer(5);
        ca.display();
    }
}

class LRUCache {

    final LinkedHashSet<Integer> cache ;
    int capacity;
    LRUCache(int capacity){
        this.capacity=capacity;
        cache = new LinkedHashSet<>(capacity);
    }


    public boolean get(int key) {
        if(cache.contains(key)){
            cache.remove(key);
            cache.add(key);
            return true;
        }
        return  false;
    }

    public boolean put(int key){
        if (cache.contains(key)){
            cache.remove(key);
        }
        if(cache.size()==capacity){
            //Cache is full remove lease recent
            cache.remove(cache.iterator().next());
        }
        cache.add(key);
        return true;
    }
    public void refer(int r){
        if(!get(r)){
            put(r);
        }
        //If present return
    }

    public void display(){
        Iterator<Integer> it = cache.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
        System.out.println();
    }
}
