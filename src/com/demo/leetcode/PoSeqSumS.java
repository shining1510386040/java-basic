package com.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 剑指Offer 和为S的连续正数序列
 * @date 2021/5/6 18:36
 * @see
 */
public class PoSeqSumS {

    public static void main(String[] args) {
        // 滑动窗口法：left right
        int s = 100;
        List<List<Integer>> ret = getPositiveSequence(s);
        System.out.println("和为S【" + s + "】的正整数序列为：" + ret);
    }

    /**
     * 滑动窗口解法：https://blog.csdn.net/zy450271923/article/details/105324779
     */
    private static List<List<Integer>> getPositiveSequence(int s) {

        int left = 1;
        int right = 2;
        List<List<Integer>> ret = new ArrayList<>(15);
        while (left < right) {
            List<Integer> cur = new ArrayList<>(2);
            // 窗口宽度
            int width = right - left + 1;
            int sum = (left + right) * width / 2;
            if (sum == s) {
                for (int i = left; i < right; i++) {
                    cur.add(i);
                }
                ret.add(cur);
                // 左指针向右移动
                left++;
            } else if (sum < s) {
                // 右指针向右移动
                right++;
            } else {
                // 左指针向右移动
                left++;
            }
        }


        return ret;
    }
}
