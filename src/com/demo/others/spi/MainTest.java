package com.demo.others.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2021/1/4 17:08
 * @see
 */
public class MainTest {


    public static void main(String[] args) {
        // ServiceLoader java Spi 加载
        ServiceLoader<Animal> serviceLoader = ServiceLoader.load(Animal.class);
        for (Animal item : serviceLoader) {
            item.sayHi();
        }
    }
}
