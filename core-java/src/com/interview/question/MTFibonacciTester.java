package com.interview.question;

import java.util.stream.IntStream;


public class MTFibonacciTester {
    public static void main(String[] args) {
        int maxNumbers = 8;
        FibonacciPrinter printer = new FibonacciPrinter(maxNumbers);
        Token token = new Token(maxNumbers);
        Thread t1 = new Thread(new Operator(printer,token));
        t1.setName("t1");
        Thread t2 = new Thread(new Operator(printer,token));
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
class FibonacciPrinter {
    private Integer[] series = null;
    private int cur = 0;

    public FibonacciPrinter(int n) {
        series = new Integer[n];
        IntStream.range(0, n).forEach(i -> series[i] = -1);
        series[0] = 0;
        series[1] = 1;
    }

    public void print() {
        if (series[cur] == -1) {
            series[cur] = series[cur - 1] + series[cur - 2];
        }
        System.out.println(Thread.currentThread().getName() + " " + series[cur++]);
    }
}


    class Operator implements  Runnable {
        private FibonacciPrinter printer;
        private Token token;
        public Operator(FibonacciPrinter printer, Token token) {
            this.printer=printer;
            this.token=token;
        }

        @Override
        public void run() {
            while(token.acquire()){
                printer.print();
            }
        }
    }

    class Token {

        int maxTokens = 0;
        int tokenProvided = 0;
        volatile String turn = "t1";

        Token(int maxTokens){
            this.maxTokens=maxTokens;
        }
        public synchronized boolean acquire(){
            if(maxTokens==tokenProvided) return false;
            while(turn!=Thread.currentThread().getName()){
                try {
                    wait();
                    if(maxTokens==tokenProvided) return false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tokenProvided++;
            turn = turn.equalsIgnoreCase("t1")?"t2":"t1";
            notifyAll();
            return true;
        }

    }

