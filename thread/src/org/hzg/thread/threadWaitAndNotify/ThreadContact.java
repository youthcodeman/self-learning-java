package org.hzg.thread.threadWaitAndNotify;

/**
 * Created by hzgal on 2019-3-24.
 * 多线程间的通讯
 * 假设现在有两个线程，一个叫做生产者，一个叫做消费者
 * 生产者生产user对象，消费者消费user对象
 */

class UserInfo {
    public String userName;
    public String sex;
    public String phoneNum;
}

class Productor extends Thread {
    int count = 0;
    UserInfo userInfoProd;

    public Productor (UserInfo userInfoProd) {
        this.userInfoProd = userInfoProd;
    }

    @Override
    public void run() {
        while(true) {
            if(count % 2 == 0) {
                userInfoProd.userName = "张三";
                userInfoProd.sex = "男";
                userInfoProd.phoneNum = "13111111111";
            }else if(count % 2 == 1) {
                userInfoProd.userName = "韩梅梅";
                userInfoProd.sex = "女";
                userInfoProd.phoneNum = "13122222222";
            }
            count++;
        }
    }
}

class Consumer extends Thread{
    UserInfo userInfoCons;
    public Consumer (UserInfo userInfoCons) {
        this.userInfoCons = userInfoCons;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("姓名：" + userInfoCons.userName + ",性别：" + userInfoCons.sex + ",电话：" + userInfoCons.phoneNum);
        }
    }
}

public class ThreadContact {
    public static void main (String[] args) {
        UserInfo userInfo = new UserInfo();
        Productor productor = new Productor(userInfo);
        Consumer consumer = new Consumer(userInfo);

        productor.start();
        consumer.start();
    }
}
