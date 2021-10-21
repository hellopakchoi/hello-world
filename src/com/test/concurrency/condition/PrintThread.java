package com.test.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class PrintThread extends Thread {

    private final Lock lock;
    private final Condition condition;

    public PrintThread(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " come in");
        try {
            condition.await();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
