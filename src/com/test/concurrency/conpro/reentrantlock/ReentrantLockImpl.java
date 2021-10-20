package com.test.concurrency.conpro.reentrantlock;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型的可重入锁实现
 */
public class ReentrantLockImpl {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition proCondition = LOCK.newCondition();
    private static final Condition conCondition = LOCK.newCondition();
    private static final ResourcePool RESOURCE_POOL = new ResourcePool(7, LOCK, proCondition, conCondition);

    public static void main(String[] args) {
        Thread p1 = new Thread(new Producer(RESOURCE_POOL));
        Thread p2 = new Thread(new Producer(RESOURCE_POOL));
        Thread p3 = new Thread(new Producer(RESOURCE_POOL));
        Thread c1 = new Thread(new Consumer(RESOURCE_POOL));
        Thread c2 = new Thread(new Consumer(RESOURCE_POOL));
        Thread c3 = new Thread(new Consumer(RESOURCE_POOL));
        Thread c4 = new Thread(new Consumer(RESOURCE_POOL));
        Thread c5 = new Thread(new Consumer(RESOURCE_POOL));
        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
    }
}

/**
 * 资源池
 */
class ResourcePool {
    private final LinkedList<Object> list = new LinkedList<>();
    private int size;
    private final int capacity;
    private final ReentrantLock lock;
    private final Condition proCondition;
    private final Condition conCondition;

    public ResourcePool(int capacity, ReentrantLock lock, Condition proCondition, Condition conCondition) {
        this.capacity = capacity;
        this.lock = lock;
        this.proCondition = proCondition;
        this.conCondition = conCondition;
    }

    /**
     * 当池子没到最大容量时，将资源放入池中
     * 当池子满了时，阻塞生产者，直到有消费者消费了资源
     * @param resource 资源
     */
    public void add(Object resource) throws InterruptedException {
        try {
            lock.lock();
            if (capacity >= size + 1) {
                list.add(resource);
                System.out.println(Thread.currentThread().getName() + " produce a resource: " + resource);
                System.out.println(list);
                size++;
                conCondition.signalAll();
            } else {
                System.out.println(Thread.currentThread().getName() + " producer begin waiting");
                proCondition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 当池子有资源时，从池中消费(移除)一个资源
     * 当池子为空时，阻塞消费者，直到生产者生产了资源
     */
    public void remove() throws InterruptedException {
        try {
            lock.lock();
            if (size > 0) {
                System.out.println(Thread.currentThread().getName() + " consume a resource: " + list.poll());
                System.out.println(list);
                size--;
                proCondition.signalAll();
            } else {
                System.out.println(Thread.currentThread().getName() + " consumer begin waiting");
                conCondition.await();
            }
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {
    private final ResourcePool resourcePool;
    private static final Random RANDOM = new Random();

    public Producer(ResourcePool resourcePool) {
        this.resourcePool = resourcePool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(RANDOM.nextInt(1500));
                // 随机产生100以内的数字作为资源
                resourcePool.add(RANDOM.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {
    private final ResourcePool resourcePool;
    private static final Random RANDOM = new Random();

    public Consumer(ResourcePool resourcePool) {
        this.resourcePool = resourcePool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(RANDOM.nextInt(2000));
                resourcePool.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
