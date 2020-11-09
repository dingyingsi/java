package com.dys.thread;


/**
 * wait()与 sleep()的区别？
 * sleep()来自 Thread 类，wait()来自 Object 类；
 * 调用 sleep()方法，线程不会释放对象锁。而调用 wait 方法线程会释放对象锁；
 * sleep()睡眠后不出让系统资源，wait 让其他线程可以占用 CPU；
 * sleep(milliseconds)需要指定一个睡眠时间，时间一到会自动唤醒。而 wait()需要配合 notify()
 * 或者 notifyAll()使用。
 *
 *
 */
public class TraditionalThreadCommunication {

  public static void main(String[] args) {
     final Business business = new Business();
     new Thread(
          new Runnable() {
            @Override
            public void run() {
              for(int i=1;i<=50;i++){
                 business.sub(i);
              }
            }
          }
     ).start();
     for(int i=1;i<=50;i++){
       business.main(i);
     }
  }
}
class Business {
  private boolean bShouldSub = true;
  public synchronized void sub(int i){
    while(!bShouldSub){
       try {
       this.wait();
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
    }
     for(int j=1;j<=10;j++){
       System.out.println("sub thread sequence of " + j + ",loop of " + i);
     }
    bShouldSub = false;
    this.notify();
  }
  
  public synchronized void main(int i){
     while(bShouldSub){
       try {
          this.wait();
       } catch (InterruptedException e) {
          e.printStackTrace();
       }
     }
     for(int j=1;j<=100;j++){
       System.out.println("main thread sequence of " + j + ",loop of " + i);
     }
     bShouldSub = true;
     this.notify();
  }
}
