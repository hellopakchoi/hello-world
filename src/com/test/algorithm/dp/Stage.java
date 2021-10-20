package com.test.algorithm.dp;

import java.util.Scanner;

/**
 * 跳台阶，一次可以走3步，问n阶台阶几种走法
 */
public class Stage {
    public static void main(String[] args) {
        //System.out.println(f(1000));
        System.out.println(fLoop(1000));
        System.out.println(fLoop1(1000));
    }

    private static long fLoop(int n) {
        long[] memory = new long[n + 1];
        memory[0] = 1;
        memory[1] = 1;
        memory[2] = 2;
        for (int i = 3; i <= n; i++) {
            memory[i] = memory[i - 1] + memory[i - 2] + memory[i - 3];
        }
        return memory[n];
    }

    private static long fLoop1(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        long f1 = 1, f2 = 1, f3 = 2, fn = 2;
        for (int i = 3; i <= n; i++) {
            fn = f1 + f2 + f3;
            f1 = f2;
            f2 = f3;
            f3 = fn;
        }
        return fn;
    }

    private static int f(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n >= 3) {
            return f(n - 1) + f(n - 2) + f(n - 3);
        }
        return 0;
    }
}
