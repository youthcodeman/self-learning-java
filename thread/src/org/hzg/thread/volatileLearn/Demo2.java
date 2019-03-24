package org.hzg.thread.volatileLearn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzgal on 2019-3-22.
 */

class VolatileDemo2 extends Thread{
    private volatile static int count;
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
        System.out.println("线程" + currentThread().getName() + "执行结束，结果为：" + count);
    }
}
public class Demo2 {
    public static void main(String[] args) {
        List<VolatileDemo2> thradList = new ArrayList<VolatileDemo2>(10);
        for (int i = 0; i < 10; i++) {
            thradList.add(new VolatileDemo2());
        }

        for (VolatileDemo2 volatileDemo2 : thradList) {
            volatileDemo2.start();
        }
    }
}
