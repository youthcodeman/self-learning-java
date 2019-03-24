package org.hzg.thread.countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hzgal on 2019-3-5.
 */
public abstract class DangerCenter implements Runnable {
    private CountDownLatch countDownLatch;      //计数器
    private String station;                      //危险品调度站
    private boolean isOkToSentCar;              //检查结果，是否满足发车条件

    public DangerCenter(CountDownLatch countDownLatch,String stationName) {
        this.countDownLatch = countDownLatch;
        this.station = stationName;
        this.isOkToSentCar = false;
    }

    @Override
    public void run() {
        try {
            check();
            isOkToSentCar = true;
        }catch (Exception e) {
            e.printStackTrace();
            isOkToSentCar = false;
        }finally {
            if(countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    /**
     * 定义抽象的check方法，用于实现了DangerCenter的各级调度中心来重写check方法，因为每个调度中心自检的方法可能不一样
     */
    public abstract void check();

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public boolean isOkToSentCar() {
        return isOkToSentCar;
    }

    public void setOkToSentCar(boolean okToSentCar) {
        isOkToSentCar = okToSentCar;
    }
}
