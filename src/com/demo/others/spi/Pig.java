package com.demo.others.spi;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2021/1/4 16:04
 * @see
 */
public class Pig implements Animal {

    @Override
    public String sayHi() {
        System.out.println("I am pig...");
        return "I am pig";
    }
}
