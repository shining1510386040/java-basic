package com.demo.others;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2020/12/18 14:18
 * @see
 */
public class Cat extends Animal {

    Cat(){
        System.out.println(" I am child constructor !");
    }

    {
        System.out.println(" I am chid static block!");
    }

    public static void main(String[] args) {
        new Cat();
//        I am parent static block!
//                I am parent constructor
//        I am chid static block!
//                I am child constructor !

        // 先构造父类 再构造子类；
        // 构造时，先执行静态块，在执行构造方法
    }
}
