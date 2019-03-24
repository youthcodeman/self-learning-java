package org.hzg.thread.threadWaitAndNotify;

/**
 * Created by hzgal on 2019-3-24.
 */
class UserInfo1 {
    public String userName;
    public String sex;
    public String phoneNum;
}

class Productor1 extends Thread {
    int count = 0;
    UserInfo1 userInfoProd;

    public Productor1 (UserInfo1 userInfoProd) {
        this.userInfoProd = userInfoProd;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (userInfoProd) {
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
            }
        }

    }
}

class Consumer1 extends Thread{
    UserInfo1 userInfoCons;
    public Consumer1 (UserInfo1 userInfoCons) {
        this.userInfoCons = userInfoCons;
    }

    @Override
    public void run() {
        synchronized (userInfoCons) {
            while (true) {
                System.out.println("姓名：" + userInfoCons.userName + ",性别：" + userInfoCons.sex + ",电话：" + userInfoCons.phoneNum);
            }
        }
    }
}

public class ThreadContact1 {
    public static void main (String[] args) {
        UserInfo1 userInfo1 = new UserInfo1();
        Productor1 productor = new Productor1(userInfo1);
        Consumer1 consumer = new Consumer1(userInfo1);

        productor.start();
        consumer.start();
    }
}
