package com.test.thread.cyclicbarrier;

import java.util.concurrent.*;

public class MainTest {
    private static final int NUM = 9;
    private static final CyclicBarrier BARRIER = new CyclicBarrier(4);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newScheduledThreadPool(NUM);
        for (int i = 0; i < NUM; i ++) {
            int finalI = i;
            executor.submit(() -> {
                try {
                    doSomething(finalI);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(finalI + " has no companion");
                }
            });
        }
        System.out.println("main thread now");
        executor.shutdown();
    }

    private static void doSomething(int num) throws BrokenBarrierException, InterruptedException, TimeoutException {
        System.out.println(num + " is doing ");
        BARRIER.await(3, TimeUnit.SECONDS);
        System.out.println(num + " is done ");
    }
}
