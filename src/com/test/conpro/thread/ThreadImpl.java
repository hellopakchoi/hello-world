package com.test.conpro.thread;

import java.util.LinkedList;
import java.util.Random;

/**
 * 生产者消费者模型，使用线程的wait()/notifyAll()实现
 */
public class ThreadImpl {
    public static void main(String[] args) {
        ResourcePool resourcePool = new ResourcePool(7);
        Thread p1 = new Thread(new Producer(resourcePool));
        Thread p2 = new Thread(new Producer(resourcePool));
        Thread p3 = new Thread(new Producer(resourcePool));
        Thread c1 = new Thread(new Consumer(resourcePool));
        Thread c2 = new Thread(new Consumer(resourcePool));
        Thread c3 = new Thread(new Consumer(resourcePool));
        Thread c4 = new Thread(new Consumer(resourcePool));
        Thread c5 = new Thread(new Consumer(resourcePool));
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

    public ResourcePool(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 当池子没到最大容量时，将资源放入池中
     * 当池子满了时，阻塞生产者，直到有消费者消费了资源
     * @param resource 资源
     */
    public synchronized void add(Object resource) throws InterruptedException {
        if (capacity >= size + 1) {
            list.add(resource);
            System.out.println(Thread.currentThread().getName() + " produce a resource: " + resource);
            System.out.println(list);
            size++;
            notifyAll();
        } else {
            System.out.println(Thread.currentThread().getName() + " producer begin waiting");
            wait();
        }
    }

    /**
     * 当池子有资源时，从池中消费(移除)一个资源
     * 当池子为空时，阻塞消费者，直到生产者生产了资源
     */
    public synchronized void remove() throws InterruptedException {
        if (size > 0) {
            System.out.println(Thread.currentThread().getName() + " consume a resource: " + list.poll());
            System.out.println(list);
            size--;
            notifyAll();
        } else {
            System.out.println(Thread.currentThread().getName() + " consumer begin waiting");
            wait();
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
