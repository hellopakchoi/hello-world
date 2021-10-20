package com.test.algorithm.dp;

/**
 * 泰波那契
 * T(0) = 0, T(1) = 1, T(2) = 1, 且在 n >= 时，T(n + 3) = T(n) + T(n + 1) + T(n + 2)
 */
public class TribonacciSeq {
    public static void main(String[] args) {
        System.out.println(tribonacci(4));
        System.out.println(tribonacci(25));
    }

    // t(n) = t(n - 1) + t(n - 2) + t(n - 3)
    public static int tribonacci(int n) {
        if (n < 2) {
            return n;
        }
        int t0 = 0, t1 = 1, t2 = 1;
        for (int i = 3; i <= n; i++) {
            int tn = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = tn;
        }
        return t2;
    }
}
