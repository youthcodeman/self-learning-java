package org.hzg.thread.countdownLatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hzgal on 2019-3-5.
 */
public class Demo {
    public static void main(String[] args) {
        boolean isAllOk = true;

        List<DangerCenter> stationList = new ArrayList<DangerCenter>();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        stationList.add(new BeijingStation(countDownLatch));
        stationList.add(new ShandongStation(countDownLatch));
        stationList.add(new ShanxiStation(countDownLatch));

        Executor stationThreadPool = Executors.newFixedThreadPool(3);

        for(DangerCenter stationCenter : stationList) {
            stationThreadPool.execute(stationCenter);
        }

        try {
            countDownLatch.await();
        }catch (Exception e) {
            e.printStackTrace();
        }

        for(DangerCenter stationCenter : stationList) {
            if(!stationCenter.isOkToSentCar()) {
                isAllOk = false;
            }
        }

        if (isAllOk) {
            System.out.println("所有站点检查完毕，可以正常进行发车！！！！！！！！！");
        }
    }
}
