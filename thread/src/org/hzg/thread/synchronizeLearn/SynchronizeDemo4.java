package org.hzg.thread.synchronizeLearn;

/**
 * Created by hzgal on 2019-3-24.
 */
class TicketSaleWindowDemo4 implements Runnable{
    private static int ticketCount = 1000;
    private String lock = "needToGetLock";

    @Override
    public void run() {
//        synchronized (lock) {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            while(ticketCount > 0) {
//                System.out.println("窗口" + Thread.currentThread().getName() + "出售：第" + (1000 - ticketCount + 1) + "张票。");
//                ticketCount--;
//            }
//        }
        saleTicket();
    }

    private synchronized static void saleTicket() {
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
public class SynchronizeDemo4 {
    public static void main(String[] args) {
        TicketSaleWindowDemo4 ticketSaleWindow = new TicketSaleWindowDemo4();

        Thread t1 = new Thread(ticketSaleWindow, "①号窗口");
        Thread t2 = new Thread(ticketSaleWindow, "②号窗口");

        t1.start();
        t2.start();
    }
}
