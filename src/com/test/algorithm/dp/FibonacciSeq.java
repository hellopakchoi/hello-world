package com.test.algorithm.dp;

public class FibonacciSeq {
    public static void main(String[] args) {
        System.out.println(fib(4));
        System.out.println(fib1(4));
    }

    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int fib1(int n) {
        if (n < 2) {
            return n;
        }
        int p, q = 0, r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
