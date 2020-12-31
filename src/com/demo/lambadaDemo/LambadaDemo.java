package com.demo.lambadaDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2020/12/22 10:12
 * @see
 */
public class LambadaDemo {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 5, 5, 7, 8, 9, 9, 78);

        List<Integer> collect = list.stream().filter(e -> e > 4).collect(Collectors.toList());

        System.out.println(collect);


    }
}
