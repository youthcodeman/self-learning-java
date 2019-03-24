package org.hzg.thread.volatileLearn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hzgal on 2019-3-22.
 */

class VolatileDemo3 extends Thread{
    private volatile static AtomicInteger count = new AtomicInteger(0);
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count.incrementAndGet();
        }
        System.out.println("线程" + currentThread().getName() + "执行结束，结果为：" + count.get());
    }
}
public class Demo3 {
    public static void main(String[] args) {
        List<VolatileDemo3> thradList = new ArrayList<VolatileDemo3>(10);
        for (int i = 0; i < 10; i++) {
            thradList.add(new VolatileDemo3());
        }

        for (VolatileDemo3 volatileDemo3 : thradList) {
            volatileDemo3.start();
        }
    }
}
