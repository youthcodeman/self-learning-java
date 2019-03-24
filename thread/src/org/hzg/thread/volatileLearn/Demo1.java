package org.hzg.thread.volatileLearn;

/**
 * Created by hzgal on 2019-3-21.
 */

class ThreadVolatileDemo extends Thread {
    public volatile boolean flag = true;
    @Override
    public void run() {
        System.out.println("ThreadVolatileDemo线程开始执行....");
        while (flag) {
        }
        System.out.println("ThreadVolatileDemo线程停止");
    }
    public void stopThread() {
        this.flag = false;
    }

}

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();
        Thread.sleep(3000);
        threadVolatileDemo.stopThread();
        System.out.println("flag 已经设置成false");
        Thread.sleep(1000);
        System.out.println(threadVolatileDemo.flag);
    }
}
