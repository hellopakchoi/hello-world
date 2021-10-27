package com.test.algorithm.dp;

/**
 * lc.740
 */
public class DeleteAndEarn {
    public static void main(String[] args) {
        int[] nums = {2,2,3,3,3,4};
        System.out.println(f(nums));
    }

    public static int f(int[] nums) {
        int max = nums[0];
        for (int i : nums) {
            max = Math.max(max, i);
        }
        int[] mapping = new int[max + 1];
        for (int i : nums) {
            mapping[i] += i;
        }
        int f1 = mapping[0], fn = Math.max(mapping[0], mapping[1]);
        for (int i = 2; i < max + 1; i++) {
            int tmp = fn;
            fn = Math.max(f1 + mapping[i], fn);
            f1 = tmp;
        }
        return fn;
    }

    /**
     * [2,2,3,3,3,4]
     * [0,0,4,9,4]
     */
}
