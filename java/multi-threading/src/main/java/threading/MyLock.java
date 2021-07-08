package threading;

import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

public class MyLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    public synchronized  void lock(){
        while(isLocked){
                if(this.lockingThread==Thread.currentThread()){
                    return;
                }
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            isLocked = true;
            lockingThread = Thread.currentThread();
        }

    public void unlock(){
        synchronized (this){
            if(lockingThread!=Thread.currentThread()){
                System.out.println("??Illegal Monitor State");
            }else{
                System.out.println("unlocking "+Thread.currentThread().getName());
                isLocked=false;
                lockingThread=null;
                this.notifyAll();
            }
        }
    }
}

class Demo{
    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        IntStream.range(0,3).forEach(x->new Thread(new  MyTask(myLock),"T"+x).start());
    }
}

class MyTask  implements  Runnable {
    MyLock sharedLock;
    public MyTask(MyLock sharedLock) {
        this.sharedLock=sharedLock;
    }

    @Override
    public void run() {
        sharedLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Start");
            Thread.sleep(1);
            System.out.println(Thread.currentThread().getName() + " End");
        } catch (InterruptedException e) {
            System.out.println("Illegal State for "+Thread.currentThread().getName());
            //e.printStackTrace();
        }
        sharedLock.unlock();
    }
}
