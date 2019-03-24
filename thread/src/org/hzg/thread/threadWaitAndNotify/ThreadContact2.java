package org.hzg.thread.threadWaitAndNotify;

/**
 * Created by hzgal on 2019-3-24.
 */

class UserInfo2 {
    public String userName;
    public String sex;
    public String phoneNum;

    public boolean isConsumerNotify = true;  //线程通讯标志
}

class Productor2 extends Thread {
    int count = 0;
    UserInfo2 userInfoProd;

    public Productor2 (UserInfo2 userInfoProd) {
        this.userInfoProd = userInfoProd;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (userInfoProd) {
                if(userInfoProd.isConsumerNotify) {
                    userInfoProd.notify();
                    if (count % 2 == 0) {
                        userInfoProd.userName = "张三";
                        userInfoProd.sex = "男";
                        userInfoProd.phoneNum = "13111111111";
                    } else if (count % 2 == 1) {
                        userInfoProd.userName = "韩梅梅";
                        userInfoProd.sex = "女";
                        userInfoProd.phoneNum = "13122222222";
                    }
                    count++;
                    userInfoProd.isConsumerNotify = false;
                    try {
                        userInfoProd.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        userInfoProd.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Consumer2 extends Thread{
    UserInfo2 userInfoCons;
    public Consumer2 (UserInfo2 userInfoCons) {
        this.userInfoCons = userInfoCons;
    }

    @Override
    public void run() {
        synchronized (userInfoCons) {

            while (true) {
                if(!userInfoCons.isConsumerNotify) {
                    userInfoCons.notify();
                    System.out.println("姓名：" + userInfoCons.userName + ",性别：" + userInfoCons.sex + ",电话：" + userInfoCons.phoneNum);
                    userInfoCons.isConsumerNotify = true;
                    try {
                        userInfoCons.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        userInfoCons.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

public class ThreadContact2 {
    public static void main (String[] args) {
        UserInfo2 userInfo2 = new UserInfo2();
        Productor2 productor = new Productor2(userInfo2);
        Consumer2 consumer = new Consumer2(userInfo2);

        productor.start();
        consumer.start();
    }
}
