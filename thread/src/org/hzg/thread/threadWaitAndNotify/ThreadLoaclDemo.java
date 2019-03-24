package org.hzg.thread.threadWaitAndNotify;

/**
 * Created by hzgal on 2019-3-25.
 */
class Number {
    public static Integer count = 0;
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        };
    };

    public Integer getNum() {
        int count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count;
    }
}

class NumberThread extends Thread {
    private Number number;

    public NumberThread(Number number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + "i---" + i + "--num:" + number.getNum());
        }

    }
}
public class ThreadLoaclDemo {
    public static void main(String[] args) {
        Number number = new Number();
        NumberThread threadLocaDemo1 = new NumberThread(number);
        NumberThread threadLocaDemo2 = new NumberThread(number);
        NumberThread threadLocaDemo3 = new NumberThread(number);

        threadLocaDemo1.start();
        threadLocaDemo2.start();
        threadLocaDemo3.start();
    }
}

