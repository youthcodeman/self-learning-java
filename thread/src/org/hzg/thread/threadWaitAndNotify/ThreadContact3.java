package org.hzg.thread.threadWaitAndNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hzgal on 2019-3-24.
 */

class UserInfo3 {
    public String userName;
    public String sex;
    public String phoneNum;

    public boolean isConsumerNotify = true;  //线程通讯标志
    Lock lock  = new ReentrantLock();
}

class Productor3 extends Thread {
    int count = 0;
    UserInfo3 userInfoProd;
    Condition condition;

    public Productor3 (UserInfo3 userInfoProd,Condition condition) {
        this.userInfoProd = userInfoProd;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                userInfoProd.lock.lock(); //上锁
                if(userInfoProd.isConsumerNotify) {
                    condition.signal(); //唤醒
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
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {

            }finally {
                userInfoProd.lock.unlock();
            }
        }
    }
}

class Consumer3 extends Thread{
    UserInfo3 userInfoCons;
    Condition condition;
    public Consumer3 (UserInfo3 userInfoCons,Condition condition) {
        this.userInfoCons = userInfoCons;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            while (true) {
                userInfoCons.lock.lock();
                if(!userInfoCons.isConsumerNotify) {
                    condition.signal();
                    System.out.println("姓名：" + userInfoCons.userName + ",性别：" + userInfoCons.sex + ",电话：" + userInfoCons.phoneNum);
                    userInfoCons.isConsumerNotify = true;
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e) {
        }finally {
            userInfoCons.lock.unlock();
        }
    }
}

public class ThreadContact3 {
    public static void main (String[] args) {
        UserInfo3 userInfo3 = new UserInfo3();
        Condition condition = userInfo3.lock.newCondition();
        Productor3 productor = new Productor3(userInfo3,condition);
        Consumer3 consumer = new Consumer3(userInfo3,condition);

        productor.start();
        consumer.start();
    }
}
