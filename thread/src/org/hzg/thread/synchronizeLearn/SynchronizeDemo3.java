package org.hzg.thread.synchronizeLearn;

/**
 * Created by hzgal on 2019-3-24.
 */

class TicketSaleWindowDemo3 implements Runnable{
    private int ticketCount = 1000;
    private String threadLock = "mustToGetLock";

    @Override
    public void run() {
        synchronized (threadLock) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(ticketCount > 0) {
                System.out.println("窗口" + Thread.currentThread().getName() + "出售：第" + (1000 - ticketCount + 1) + "张票。");
                ticketCount--;
            }
        }
    }
}
public class SynchronizeDemo3 {
    public static void main(String[] args) {
        TicketSaleWindowDemo3 ticketSaleWindow = new TicketSaleWindowDemo3();

        Thread t1 = new Thread(ticketSaleWindow, "①号窗口");
        Thread t2 = new Thread(ticketSaleWindow, "②号窗口");

        t1.start();
        t2.start();
    }
}
