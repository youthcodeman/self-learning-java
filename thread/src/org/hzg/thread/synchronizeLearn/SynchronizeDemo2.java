package org.hzg.thread.synchronizeLearn;

/**
 * Created by hzgal on 2019-3-24.
 * 需求现在有100张火车票，有两个窗口同时抢火车票，请使用多线程模拟抢票效果。
 */

class TicketSaleWindowDemo2 implements Runnable{
    private int ticketCount = 100;

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(ticketCount > 0) {
            System.out.println("窗口" + Thread.currentThread().getName() + "出售：第" + (100 - ticketCount + 1) + "张票。");
            ticketCount--;
        }
    }
}

public class SynchronizeDemo2 {
    public static void main(String[] args) {
        TicketSaleWindowDemo2 ticketSaleWindow = new TicketSaleWindowDemo2();

        Thread t1 = new Thread(ticketSaleWindow, "①号窗口");
        Thread t2 = new Thread(ticketSaleWindow, "②号窗口");

        t1.start();
        t2.start();
    }
}
