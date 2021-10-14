package com.test.algorithm.dp;

/**
 * 矩阵（m, n）m->n 最短路径，
 *  1 3 1 7
 *  1 5 1 6
 *  4 2 1 5
 *  1-3-1-1-1-5 = 12
 */
public class ShortestPath {
    public static void main(String[] args) {
        //int[][] matrix = {{1, 3, 1, 7}, {1, 5, 1, 6}, {4, 2, 1, 5}};
        int[][] matrix = {{3,8,6,0,5,9,9,6,3,4,0,5,7,3,9,3},{0,9,2,5,5,4,9,1,4,6,9,5,6,7,3,2},{8,2,2,3,3,3,1,6,9,1,1,6,6,2,1,9},{1,3,6,9,9,5,0,3,4,9,1,0,9,6,2,7},{8,6,2,2,1,3,0,0,7,2,7,5,4,8,4,8},{4,1,9,5,8,9,9,2,0,2,5,1,8,7,0,9},{6,2,1,7,8,1,8,5,5,7,0,2,5,7,2,1},{8,1,7,6,2,8,1,2,2,6,4,0,5,4,1,3},{9,2,1,7,6,1,4,3,8,6,5,5,3,9,7,3},{0,6,0,2,4,3,7,6,1,3,8,6,9,0,0,8},{4,3,7,2,4,3,6,4,0,3,9,5,3,6,9,3},{2,1,8,8,4,5,6,5,8,7,3,7,7,5,8,3},{0,7,6,6,1,2,0,3,5,0,8,0,8,7,4,3},{0,4,3,4,9,0,1,9,7,7,8,6,4,6,9,5},{6,5,1,9,9,2,2,7,4,2,7,2,2,3,7,2},{7,1,9,6,1,2,7,0,9,6,6,4,4,5,1,0},{3,4,9,2,8,3,1,2,6,9,7,0,2,4,2,0},{5,1,8,8,4,6,8,5,2,4,1,6,2,2,9,7}};
        //System.out.println(f(matrix, matrix.length - 1, matrix[0].length - 1));
        //System.out.println(f(matrix, 1, 3));
        System.out.println(f(matrix));
    }

    private static int f(int[][] matrix, int x, int y) {
        if (x == 0 && y == 0) {
            return matrix[0][0];
        } else if (x == 0) {
            return matrix[0][y] + f(matrix, 0, y - 1);
        } else if (y == 0) {
            return matrix[x][0] + f(matrix, x - 1, 0);
        } else {
            int left = f(matrix, x - 1, y);
            int up = f(matrix, x, y - 1);
            return Math.min(left, up) + matrix[x][y];
        }
    }

    public static int f(int[][] grid) {
        int[][] counting = new int[grid.length][grid[0].length];
        counting[0][0] = grid[0][0];
        // 计算出第0行每一步的路径和
        for (int x = 1; x < counting.length; x++) {
            counting[x][0] = counting[x - 1][0] + grid[x][0];
        }
        // 第0列每一步的路径和
        for (int y = 1; y < counting[0].length; y++) {
            counting[0][y] = counting[0][y - 1] + grid[0][y];
        }
        // 非0行非0列的最短路径和
        for (int x = 1; x < counting.length; x++) {
            for (int y = 1; y < counting[x].length; y++) {
                // 到当前位置的最短路径
                int min = Math.min(counting[x - 1][y], counting[x][y - 1]);
                counting[x][y] = min + grid[x][y];
            }
        }
        return counting[counting.length - 1][counting[0].length - 1];
    }
}
