package com.test.conpro;

import java.util.LinkedList;
import java.util.Random;

public class ThreadImpl {
    public static void main(String[] args) {
        Resource resource = new Resource();
        resource.setList(new LinkedList<>());
        resource.setCapacity(10);
        Thread p1 = new Thread(new Producer(resource));
        Thread p2 = new Thread(new Producer(resource));
        Thread p3 = new Thread(new Producer(resource));
        Thread c1 = new Thread(new Consumer(resource));
        Thread c2 = new Thread(new Consumer(resource));
        Thread c3 = new Thread(new Consumer(resource));
        Thread c4 = new Thread(new Consumer(resource));
        Thread c5 = new Thread(new Consumer(resource));
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

class Resource {
    private LinkedList<Object> list;
    private int size;
    private int capacity;

    public synchronized void add(Object resource) throws InterruptedException {
        if (capacity >= size + 1) {
            list.push(resource);
            size++;
            System.out.println(Thread.currentThread().getName() + " produce a resource");
            System.out.println(list);
            notifyAll();
        } else {
            System.out.println(Thread.currentThread().getName() + " producer begin waiting");
            wait();
        }
    }

    public synchronized void remove() throws InterruptedException {
        if (size > 0) {
            list.removeLast();
            size--;
            System.out.println(Thread.currentThread().getName() + " consume a resource");
            System.out.println(list);
            notifyAll();
        } else {
            System.out.println(Thread.currentThread().getName() + " consumer begin waiting");
            wait();
        }
    }

    public void setList(LinkedList<Object> list) {
        this.list = list;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

class Producer implements Runnable {
    private final Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                resource.add(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

class Consumer implements Runnable {
    private final Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                resource.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
