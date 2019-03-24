package org.hzg.thread.volatileLearn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzgal on 2019-3-22.
 */
class VolatileDemo4 extends Thread{
    private volatile static int count;
    private static final String lock = "countLock";

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            System.out.println("线程" + currentThread().getName() + "执行结束，结果为：" + count);
        }
    }
}
public class Demo4 {
    public static void main(String[] args) {
        List<VolatileDemo4> thradList = new ArrayList<VolatileDemo4>(10);
        for (int i = 0; i < 10; i++) {
            thradList.add(new VolatileDemo4());
        }

        for (VolatileDemo4 volatileDemo4 : thradList) {
            volatileDemo4.start();
        }
    }
}
