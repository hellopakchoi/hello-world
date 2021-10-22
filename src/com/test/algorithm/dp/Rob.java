package com.test.algorithm.dp;

/**
 * 打家劫舍
 * 给定数组，相邻不可取，求最大和
 */
public class Rob {
    public static void main(String[] args) {
        //int[] nums = {2,7,9,3,1};
        //int[] nums = {2,1,1,2};
        int[] nums = {1,2,3,1};
        //int[] nums = {2,3,2};
        //int[] nums = {3,1,1,1,1,3};
        System.out.println(robLoop(nums));
        //System.out.println(rob1(nums));
        //System.out.println(rob(nums));
    }

    private static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        nums[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 2] + nums[i], nums[i - 1]);
        }
        return nums[nums.length - 1];
    }

    private static int rob1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int f1 = nums[0], fn = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int tmp = fn;
            fn = Math.max(f1 + nums[i], fn);
            f1 = tmp;
        }
        return fn;
    }

    private static int robLoop(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dest = new int[nums.length - 1];
        System.arraycopy(nums, 1, dest, 0, nums.length - 1);
        int x1 = rob1(dest);
        System.arraycopy(nums, 0, dest, 0, nums.length - 1);
        int x2 = rob1(dest);
        return Math.max(x1, x2);
    }
}
