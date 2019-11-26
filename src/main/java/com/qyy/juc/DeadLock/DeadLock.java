package com.qyy.juc.DeadLock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁条件
 * 互斥使用：一个资源只能分配给一个线程
 * 不可剥夺：资源只能由占有者释放，申请者不能强制剥夺
 * 请求保持：线程申请资源时，保持对原有资源的占有
 * 循环等待：存在一个进程等待队列：{P1 , P2 , … , Pn}, 其中P1等待P2占有的资源，P2等待P3占有的资源，…，Pn等待P1占有的资源，形成一个进程等待环路
 * 代码
 */
public class DeadLock implements Runnable {

    private int flag = 0;

    //定义两个锁
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public DeadLock(int flag) {
        this.flag = flag;
    }

    public static void main(String[] args) {

        //线程1
        DeadLock dea1 = new DeadLock(0);
        DeadLock dea2 = new DeadLock(1);

        new Thread(dea1,"thread_test_1").start();
        new Thread(dea2,"thread_test_2").start();


    }


    @Override
    public void run() {
        //需要定义两个不同的run方法，这里使用标识模拟
        if(flag==0) {

            //线程1 占有锁lock2 需要 lock1
            synchronized (lock2) {
                try {
                    System.out.println("do sth in lock2");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println("do sth in lock1");
                }
            }
        }

        if(flag==1){
            //线程2 占有锁lock1 需要 lock2
            synchronized (lock1) {
                try {
                    System.out.println("do sth in lock1");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("do sth in lock2");
                }
            }
        }

    }
}
