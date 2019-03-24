package org.hzg.thread.countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hzgal on 2019-3-5.
 */
public class BeijingStation extends DangerCenter {
    private static String stationName = "北京危险品调度站";

    public BeijingStation(CountDownLatch countDownLatch) {
        super(countDownLatch,stationName);
    }

    @Override
    public void check() {
        System.out.println("北京调度站正在进行车辆自检..........");

        try {
            Thread.sleep(2000);
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("北京调度站车辆自检完毕，一切正常");
    }
}
