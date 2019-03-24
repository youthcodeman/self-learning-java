package org.hzg.thread.countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hzgal on 2019-3-5.
 */
public class ShanxiStation extends DangerCenter {
    private static String stationName = "陕西危险品调度站";

    public ShanxiStation(CountDownLatch countDownLatch) {
        super(countDownLatch,stationName);
    }

    @Override
    public void check() {
        System.out.println("陕西调度站正在进行车辆自检..........");

        try {
            Thread.sleep(3000);
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("陕西调度站车辆自检完毕，一切正常");
    }
}
