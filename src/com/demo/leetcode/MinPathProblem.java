package com.demo.leetcode;

import java.util.Arrays;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 最小路径和问题
 * @date 2021/4/22 15:36
 * @see
 */
public class MinPathProblem {

    /**
     * 从左上角到右下角，求最小路径和，只能向右向下寻找路径
     */
    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("======>>>初始矩阵：");
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
        int sum = minPath(grid);
        System.out.println("====>>最小路径和为：" + sum);

    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 动态规划解法
     * @date 2021/4/22 15:40
     */
    private static int minPath(int[][] grid) {
        //dp： 0 0 到 i j 的最短路径和
        int[][] dp = new int[grid.length][grid[0].length];
        // 初始化第一行
        for (int i = 0; i < grid.length; i++) {
            if (i == 0) {
                dp[0][0] = grid[0][0];
            } else {
                dp[i][0] = grid[i][0] + dp[i - 1][0];
            }
        }
        // 初始化第一列
        for (int i = 0; i < grid[0].length; i++) {
            if (i == 0) {
                dp[0][0] = grid[0][0];
            } else {
                dp[0][i] = grid[0][i] + dp[0][i - 1];
            }
        }
        // 填表
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                // 取向下和向右移动后的路径值得较小者
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

}
