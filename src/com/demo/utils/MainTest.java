package com.demo.utils;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试文本替换
 * @date 2021/4/22 16:13
 * @see
 */
public class MainTest {


    public static void main(String[] args) {
        String origin = "abcd.name=the report is new == ccreat ===13 =";

//        String dest = "'" + origin.replaceFirst("=", "'='") + "'";
        String dest = origin.replaceAll("(?<!=)=(?!=)", "‘=’");
        System.out.println(dest);
    }
}
