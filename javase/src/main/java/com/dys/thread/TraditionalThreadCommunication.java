package com.dys.thread;


/**
 * wait()与 sleep()的区别？
 * sleep()来自 Thread 类，wait()来自 Object 类；
 * 调用 sleep()方法，线程不会释放对象锁。而调用 wait 方法线程会释放对象锁；
 * sleep()睡眠后不出让系统资源，wait 让其他线程可以占用 CPU；
 * sleep(milliseconds)需要指定一个睡眠时间，时间一到会自动唤醒。而 wait()需要配合 notify()
 * 或者 notifyAll()使用。
 */
public class TraditionalThreadCommunication {

    public static void main(String[] args) throws Exception {
        final Business business = new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 50; i++) {
                            try {
                                business.sub(i);
                            } catch (Exception e) {}
                        }
                    }
                }
        ).start();
        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }
    }
}

class Business {
    private boolean bShouldSub = true;

    public synchronized void sub(int i) throws Exception {
        while (!bShouldSub) {
            this.wait();
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = false;
        this.notify();
    }

    public synchronized void main(int i) throws Exception {
        while (bShouldSub) {
            this.wait(); // 此处阻塞调用这个方法的线程，把线程放到这个对象对应的阻塞队列中
        }
        for (int j = 1; j <= 100; j++) {
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = true;
        this.notify();
    }
}
