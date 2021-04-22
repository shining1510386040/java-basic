package com.demo.proxy;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 学生
 * @date 2021/2/10 15:32
 * @see
 */
public class Student extends Father implements Persion {

    public void study() {
        System.out.println("i am student.");
    }

    @Override
    public void speak() {
        System.out.println("i am student ,i can speak");
    }

    @Override
    public void walk() {
        System.out.println("i am student ,i can walk");
    }
}
