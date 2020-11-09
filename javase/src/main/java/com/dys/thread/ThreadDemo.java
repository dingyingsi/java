package com.dys.thread;

/**
 * 对于第三个Thread而言，因为子类中的run()方法会override父类中的run()方法，所马会执行下面的run()方法;
 */
public class  ThreadDemo {
  
  public static void main(String[] args) throws Exception {
  
     Thread thread = new Thread(){
       @Override
       public void run() {
          while(true){
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println("1:" + Thread.currentThread().getName());
            System.out.println("2:" + this.getName());
          }
       }
     };
     thread.start();
     
     Thread thread2 = new Thread(new Runnable(){
       @Override
       public void run() {
          while(true){
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println("1:" + Thread.currentThread().getName());
          }
       }
     });
     thread2.start();
     
     new Thread(
          new Runnable(){
               @Override
            public void run() {
              while(true){
                 try {
                   Thread.sleep(500);
                 } catch (InterruptedException e) {
                   e.printStackTrace();
                 }
                 System.out.println("runnable :" + Thread.currentThread().getName());
              }                
            }
          }
     ){
          @Override
       public void run() {
          while(true){
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println("thread :" + Thread.currentThread().getName());
          } 
       }
     }.start();
  }
}
