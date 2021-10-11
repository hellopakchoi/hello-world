package com.test.thread.interrupt;

public class TestThread implements Runnable {
    private String mark;

    public TestThread(String mark) {
        this.mark = mark;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (Thread.interrupted()) {
                System.out.println(mark + " is interrupted");
                return;
            }
            System.out.println(mark + "-" + i);
        }
    }
}
