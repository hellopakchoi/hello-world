package com.test.concurrency.join;

public class TestThread implements Runnable {
    private String mark;

    public TestThread(String mark) {
        this.mark = mark;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(mark + "-" + i);
        }
    }
}
