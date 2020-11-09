package com.dys.thread;

import java.util.concurrent.CountDownLatch;

public class testLatch {
    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);
        for(int i=0; i<2; i++){
            Thread thread = new Thread(new Player(begin,end),String.valueOf(i));
            thread.start();
        }
        try{
            System.out.println("the race begin");
            begin.countDown();
            end.await();
            System.out.println("the race end");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
class Player implements Runnable{
    private CountDownLatch begin;
    private CountDownLatch end;
    public Player(CountDownLatch begin,CountDownLatch end){
        this.begin = begin;
        this.end = end;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " start !");;
            begin.await();
            System.out.println(Thread.currentThread().getName() + " arrived !");
            end.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

