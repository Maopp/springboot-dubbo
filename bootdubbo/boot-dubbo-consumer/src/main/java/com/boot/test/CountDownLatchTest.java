package com.boot.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchTest {

    private volatile int count = 0;
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(4);

        CountDownLatchTest test = new CountDownLatchTest();

        // 不能保证线程安全
        // test.testOnlyVolatile(countDownLatch);

        // 能保证线程安全
        // test.testSynchronizedAndVolatile(countDownLatch);

        // 能保证线程安全
        test.testReenTrantLockAndVolatile(countDownLatch);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test.getCount());
    }

    /**
     * 使用乐观锁方式
     * @param countDownLatch
     */
    private void testReenTrantLockAndVolatile(CountDownLatch countDownLatch) {
        Lock lock = new ReentrantLock();
        for (int i = 0;i < 4;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    countPlusReen(lock);
                    countDownLatch.countDown();
                }
            }).start();
        }
    }

    /**
     * 使用悲观锁方式
     * @param countDownLatch
     */
    private void testSynchronizedAndVolatile(CountDownLatch countDownLatch) {
        for (int i = 0;i < 4;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    countPlusSyn();
                    countDownLatch.countDown();
                }
            }).start();
        }
    }

    /**
     * 普通实现，只对共享数据添加volatile关键字休息，支持可见性
     * 还是会出现线程安全问题
     * @param countDownLatch
     */
    public void testOnlyVolatile(CountDownLatch countDownLatch) {
        countPlus(countDownLatch);
    }

    public void countPlusReen(Lock lock) {
        lock.lock();
        try {
            for (int j = 0;j < 100;j++) {
                count++;
            }
        } finally {
            lock.unlock();
        }
    }

    public synchronized void countPlusSyn() {
        for (int j = 0;j < 100;j++) {
            count++;
        }
    }

    private void countPlus(CountDownLatch countDownLatch) {
        for (int i = 0;i < 4;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0;j < 100;j++) {
                        count++;
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
    }

    public int getCount() {
        return count;
    }
}
