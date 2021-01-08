package com.demo.threadandlock.newcodePritice;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 寻找字符串子序列；非连续也算；
 * @date 2021/1/7 19:03
 * @see
 */
public class FindSubStr {

    public static void main(String[] args) {

        String sub = "abc";
        String target = "abcaybec";
        // 3
        int index = find(sub, target);

        System.out.println(index);

    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 寻找子序列，找到，返回最后一个 子序列首字母下标；
     * 找不到返回-1；
     * @date 2021/1/7 19:04
     */
    private static int find(String sub, String target) {

        char[] subChars = sub.toCharArray();
        char[] taragetChars = target.toCharArray();
        int pos = 0;

        // todo 有问题。。
        for (char subChar : subChars) {
            for (int i = pos; i < taragetChars.length; i++) {
                if (subChar == taragetChars[i]) {
                    // 下标位置
                    pos = i + 1;
                    break;
                }
            }

        }


        return pos;
    }


}
