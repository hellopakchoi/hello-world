package com.test.algorithm;

/**
 * 接雨水
 * 1.动态规划
 * 2.单调栈
 * 3.双指针
 */
public class TrapRain {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
        System.out.println(trap1(height));
    }

    // 动态规划
    private static int trap(int[] height) {
        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i < leftMax.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        int[] rightMax = new int[height.length];
        rightMax[rightMax.length - 1] = height[height.length - 1];
        for (int i = rightMax.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        int capacity = 0;
        for (int i = 0; i < height.length; i++) {
            capacity += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return capacity;
    }

    // 双指针
    private static int trap1(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int capacity = 0;
        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if (height[left] < height[right]) {
                capacity += leftMax - height[left];
                left++;
            } else {
                capacity += rightMax - height[right];
                right--;
            }
        }
        return capacity;
    }

    // 单调栈
    private static int trap2(int[] height) {

        return 0;
    }
}
