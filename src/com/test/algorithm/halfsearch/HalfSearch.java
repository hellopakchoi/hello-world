package com.test.algorithm.halfsearch;

public class HalfSearch {
    public static void main(String[] args) {
        // int[] arr = { 9, 6, 3, 2, 7, 8, 0, 4, 1, 5 };
        int[] arr = { 10, 23, 27, 58, 67, 99, 107, 129, 201 };
        System.out.println(doHalfSearch(arr, 10, 0, arr.length));
    }

    private static int doHalfSearch(int[] arr, int target) {
        int begin = 0;
        int end = arr.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (arr[mid] < target) {
                begin = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static int doHalfSearch(int[] arr, int target, int begin, int end) {
        if (end < begin) {
            return -1;
        }
        int mid = (begin + end) / 2;
        if (arr[mid] < target) {
            return doHalfSearch(arr, target, mid + 1, end);
        } else if (arr[mid] > target) {
            return doHalfSearch(arr, target, begin, mid - 1);
        } else {
            return mid;
        }
    }
}