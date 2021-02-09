package com.demo.juc;

import java.util.concurrent.atomic.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description cas 测试
 * @date 2021/2/9 14:05
 * @see
 */
public class ActomicTest {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);
        // ++i
        int a = atomicInteger.incrementAndGet();
        // i++
        int b = atomicInteger.getAndIncrement();
        // i--
        int c = atomicInteger.getAndDecrement();
        // --i
        int d = atomicInteger.decrementAndGet();
        System.out.println("a:{" + a + "},b:{" + b + "},c:{" + c + "},d:{" + d + "}");

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        int[] aa = new int[3];
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(aa);
        AtomicLong atomicLong = new AtomicLong(3L);
        long[] bb = new long[4];
        AtomicLongArray atomicLongArray = new AtomicLongArray(bb);
        long andIncrement = atomicLongArray.getAndIncrement(1);
        System.out.println(andIncrement);
        AtomicReference<String> atomicReference = new AtomicReference<>("abc");
        String bcd = atomicReference.getAndSet("bcd");
        System.out.println(bcd);

        // double sum计算
        DoubleAdder doubleAdder = new DoubleAdder();
        doubleAdder.add(3);
        doubleAdder.add(4.5);
        doubleAdder.add(-1.2);
        double sum = doubleAdder.sum();
        System.out.println("3+4.5-1.2=" + sum);
        System.out.println("3+4.5-1.2=" + (3 + 4.5 - 1.2));
        // long sum计算
        LongAdder longAdder = new LongAdder();

    }
}
