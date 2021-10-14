package com.test.algorithm.dp;

import java.util.Scanner;

/**
 * 跳台阶，一次可以走3步，问n阶台阶几种走法
 */
public class Stage {
    public static void main(String[] args) {
        //System.out.println(f(1000));
        System.out.println(fLoop(1000));
    }

    private static long fLoop(int n) {
        long[] memory = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i == 0 || i == 1) {
                memory[i] = 1;
            }
            if (i == 2) {
                memory[i] = 2;
            }
            if (i >= 3) {
                memory[i] = memory[i - 1] + memory[i - 2] + memory[i - 3];
            }
        }
        return memory[n];
    }

    private static int f(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return f(1 ) + 1;
        }
        if (n >= 3) {
            return f(n - 1) + f(n - 2) + f(n - 3);
        }
        return 0;
    }
}
