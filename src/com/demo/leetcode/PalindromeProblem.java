package com.demo.leetcode;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 回文串问题：
 * 注："回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。
 * @date 2021/4/23 9:36
 * @see
 */
public class PalindromeProblem {

    public static void main(String[] args) {

        String origin = "aabcb";
        System.out.println("===============》》》原始字符串：" + origin);
        // 1.计算一个字符串的回文子串的个数
        int dest1 = numSubstr(origin);
        System.out.println("=====>>> 回文子串的个数：" + dest1);
        // 2.寻找一个字符串的最长回文子串
        String dest2 = longestSubStr(origin);
        System.out.println("======>>>最长回文子串：" + dest2);
        // 3.寻找一个字符串的最长回文子序列（求长度）：方法2
        int dest3 = longestSubStr2(origin);
        System.out.println("===========>>>最长回文子序列：" + dest3);
    }

    private static int longestSubStr2(String origin) {
        int n = origin.length();
        // dp[i][j]: 字符串[i,j]的 回文子串的长度
        int[][] dp = new int[n][n];
        // 状态方程：dp[i][j] = dp[i+1][j-1]+2 (s[i] == s[j])
        // dp[i][j] = max{dp[i+1][j],dp[i][j-1]}
        // 状态初始化：dp[i][i] = 1
        // i 向左移动 j向右移动
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (origin.charAt(i) == origin.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][origin.length() - 1];
    }

    private static int numSubstr(String origin) {

        int n = origin.length();
        // 回文子串的个数
        int num = 0;

        // dp[i][j]: 子串[i,j] 是否为回文子串
        // 状态方程：dp[i][j] = (s[i]==s[j]) && (j-i<3 ||dp[i+1][j-1]))
        // 初始状态：dp[i][i] = true 对角线为true
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = true;
            // i 向左
            for (int j = i; j < n; j++) {
                // j 向右
                if (origin.charAt(i) == origin.charAt(j)) {
                    dp[i][j] = true && (j - i < 3 || dp[i + 1][j - 1]);
                } else {
                    dp[i][j] = false;
                }

                // 记录回文起始位置和长度
                if (dp[i][j]) {
                    num++;
                }
            }
        }

        return num;
    }

    private static String longestSubStr(String origin) {

//        在头尾字符相等的情况下，里面子串的回文性质据定了整个子串的回文性质，这就是状态转移。
//        如果前一个子串不是回文，那么即使两端的字符相等，构成的子串也不是回文。
//        所以我们需要记录前一个子串是否是回文，而不是记录前一个回文子串的长度。
//        因此可以把“状态”定义为原字符串的一个子串是否为回文子串。

        int n = origin.length();
        // 最长回文子串的起始位置和长度
        int start = 0;
        int end = 0;
        // dp[i][j]: 子串[i,j] 是否为回文子串
        // 状态方程：dp[i][j] = (s[i]==s[j]) && (j-i<3 ||dp[i+1][j-1]))
        // 初始状态：dp[i][i] = true 对角线为true
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = true;
            // i 向左
            for (int j = i; j < n; j++) {
                // j 向右
                if (origin.charAt(i) == origin.charAt(j)) {
                    dp[i][j] = true && (j - i < 3 || dp[i + 1][j - 1]);
                } else {
                    dp[i][j] = false;
                }

                // 记录回文起始位置和长度
                if (dp[i][j] && (j - i + 1 > end - start)) {
                    start = i;
                    end = j + 1;
                }
            }
        }

        return origin.substring(start, end);
    }


}
