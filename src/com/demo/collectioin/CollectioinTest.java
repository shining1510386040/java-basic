package com.demo.collectioin;

import java.util.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 可迭代集合测试
 * @date 2021/2/4 16:13
 * @see
 */
public class CollectioinTest {

    public static void main(String[] args) {

//        testList();
        testSet();
//        testQueue();
    }

    private static void testQueue() {

    }

    private static void testSet() {
        List<Integer> aList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 23);
        List<Integer> bList = Arrays.asList(11, 2, 3);
        Set first = new HashSet(aList);
        Set second = new HashSet(bList);
        System.out.println(first);
        // 1.差集
        // [1, 4, 5, 6, 7, 23, 8, 9]
//        first.removeAll(second);
        // 2.交集
        // [2, 3]
//        first.retainAll(second);
        // 3.并集，set 中语义不可重复
//        [1, 2, 3, 4, 5, 6, 7, 23, 8, 9, 11]
        first.addAll(second);

        System.out.println(first);
    }

    private static void testList() {

        List<Integer> aList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 23);
        List<Integer> bList = Arrays.asList(11, 2, 3);
        // java.lang.UnsupportedOperationException:Arrays.asList 是不可变的list，不能添加和删除元素；
//        boolean b = aList.removeAll(bList);
        System.out.println(aList);
        List first = new ArrayList(aList);
        List second = new ArrayList<>(bList);
//        // 1.1 removeAll 求差集
//        boolean b1 = first.removeAll(second);
        // 1, 4, 5, 6, 7, 8, 9, 23
//        System.out.println(first);
//        // 1.2 retainAll 求交集
//        // 2，3
//        first.retainAll(second);
//        System.out.println(first);
        first.addAll(second);
        // 1.3 求并集,list中语义 可以重复
        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 23, 11, 2, 3]
        System.out.println(first);
    }

}
