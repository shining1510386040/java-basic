package com.demo.leetcode;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 路径问题：https://blog.csdn.net/zy450271923/article/details/106014152
 * 思路：动态规划
 * @date 2021/4/22 14:35
 * @see
 */
public class PathProblem {

    public static void main(String[] args) {
        int m = 3;
        int n = 2;
        int nums = sumRoads(m, n);
        System.out.println("从左上角到右下角有：" + nums + "条路径。");

        int nums1 = sumRoads2(1, 1, m, n);
        System.out.println("从左上角到右下角有：" + nums1 + "条路径。");
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 递归解法2
     * @date 2021/4/22 14:53
     */
    private static int sumRoads2(int i, int j, int m, int n) {

        if (i == m && j == n) {
            return 1;
        } else if (i < m && j == n) {
            // j==n 只能向右走
            return sumRoads2(i + 1, j, m, n);
        } else if (i == m && j < n) {
            // i== m 只能向下走
            return sumRoads2(i, j + 1, m, n);
        } else {
            // 向右走一步的路径数+向下走的路径数
            return sumRoads2(i + 1, j, m, n) + sumRoads2(i, j + 1, m, n);
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 动态规划解法1
     * @date 2021/4/22 14:51
     */
    private static int sumRoads(int m, int n) {

        int[][] dp = new int[m][n];
        // 1.初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        // 递归赋值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 2.状态方程：赋值
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
