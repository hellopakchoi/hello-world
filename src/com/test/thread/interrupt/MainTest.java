package com.test.thread.interrupt;

public class MainTest {
    public static void main(String[] args) {
        TestThread tt1 = new TestThread("A");
        TestThread tt2 = new TestThread("B");
        Thread t1 = new Thread(tt1);
        Thread t2 = new Thread(tt2);
        t1.start();
        System.out.println("t2 starting");
        t2.start();
        t1.interrupt();
    }
}
