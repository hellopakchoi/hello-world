package com.test.concurrency.condition;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 Condition 的 signalAll 方法，会不会唤醒其他 Condition await 的线程？
 * 结果：不会。只会唤醒该 Condition await 的线程
 */
public class SignalAllTest {

    @Test
    public void t1() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition c0 = lock.newCondition();
        Condition c1 = lock.newCondition();
        PrintThread p0 = new PrintThread(lock, c0);
        PrintThread p1 = new PrintThread(lock, c1);
        PrintThread p2 = new PrintThread(lock, c0);
        PrintThread p3 = new PrintThread(lock, c1);
        PrintThread p4 = new PrintThread(lock, c0);
        PrintThread p5 = new PrintThread(lock, c1);
        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        Thread.sleep(2000);
        try {
            lock.lock();
            c1.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
