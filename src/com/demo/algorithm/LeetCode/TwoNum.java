package com.demo.algorithm.LeetCode;

import java.io.IOException;
import java.util.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 在数组中找到 2 个数之和等于给定值的数字，结果返回 2 个数字在数组中的下标。
 * @date 2021/3/12 10:57
 * @see
 */
public class TwoNum {


    /**
     * 示例：
     * Given nums = [2, 7, 11, 15], target = 9,
     * <p>
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1]
     */
    public static void main(String[] args) throws IOException {

        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
//        List position = new ArrayList(2);
//        position.add(0);
//        position.add(1);
//        List<Integer> position = find(nums, target);
//        Map data = new HashMap(2);
//        data.put(1,2);
//        data.put("username","zhansan");
//        System.out.println(data);
        List<Integer> position = getRet(nums, target);
        System.out.println(position);
//        System.in.read();
    }

    private static List<Integer> getRet(int[] nums, int target) {

        List<Map> ret = new ArrayList(2);
        for (int i = 0; i < nums.length; i++) {
            // 1.计算另一个数字的值
            int add2 = target - nums[i];
            Map<Integer,Integer> cur = new HashMap<>(1);
            cur.put(i,null);
            // 2.判断该值是否在nums数组中；若在，将下标存入返回结果集
            if (add2 == nums[i]){

            }
        }

        return null;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 不是最优解：时间复杂度O(n^2)
     * @date 2021/3/12 15:08
     */
    private static List find(int[] nums, int target) {
        List ret = new ArrayList(2);
        for (int i = 0; i < nums.length; i++) {
            ret.add(i);
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ret.add(j);
                    // 找到即返回
                    return ret;
                }
            }
        }
        return null;
    }


}
