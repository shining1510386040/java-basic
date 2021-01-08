package com.demo.threadandlock.newcodePritice;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.sql.rowset.CachedRowSet;
import java.util.Scanner;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 字符串加密；
 * @date 2021/1/7 18:44
 * @see
 */
public class StrJiaMi {

    public static void main(String[] args) {

        // 测试：xy -> ya
        // abcde -> bdgkr

        Scanner scanner = new Scanner(System.in);
        String oriStr = scanner.nextLine();
        String ret = handleStr(oriStr);
        System.out.println(ret);
    }

    /**
     * 全是小写字母：循环队列？ abcdef。。。。z
     * 26个字母；
     */
    private static String handleStr(String oriStr) {
        char[] source = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] chars = oriStr.toCharArray();
        char[] newChars = new char[oriStr.length()];
        for (int i = 0; i < chars.length; i++) {
            int off = getOffset(i + 1);
            // 查找新字符
            for (int j = 0; j < source.length; j++) {
                if (chars[i] == source[j]) {
                    int tem = (j + off) % 26;
                    // 加密偏移
                    newChars[i] = source[tem];
                }
            }
        }
        return new String(newChars);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 计算偏移量
     * 算法：a[1] = 1,a[2] = 2,a[3] = 4
     * a[i] = a[i-1]+a[i-2]+a[i-3]
     * <p>
     * 1 2 4 7 13
     * @date 2021/1/7 18:48
     */
    private static int getOffset(int index) {

        if (index == 1) {
            return 1;
        } else if (index == 2) {
            return 2;
        } else if (index == 3) {
            return 4;
        } else {
            return getOffset(index - 1) + getOffset(index - 2) + getOffset(index - 3);
        }
    }
}
