package com.demo.leetcode;

import java.util.Arrays;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 矩阵转置：思路,遍历二维数组然后赋值
 * @date 2021/4/22 11:13
 * @see
 */
public class MatrixTransfer {

    public static void main(String[] args) {

        // 矩阵是个 二维数组（M * N）
//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = {{1, 2}, {4, 5}, {7, 8}};
        int m = matrix.length;
        int n = matrix[0].length;
        System.out.println("==========转置前：");
        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }

        System.out.println("============转置后：");
        int[][] ret = transfer(matrix);
        // 转置后
        for (int i = 0; i < ret.length; i++) {
            System.out.println(Arrays.toString(ret[i]));
        }
    }

    private static int[][] transfer(int[][] matrix) {
        // m*n -> n*m
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ret = new int[n][m];
        // 设置ret的值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 遍历然后赋值
                ret[j][i] = matrix[i][j];
            }
        }

        return ret;
    }
}
