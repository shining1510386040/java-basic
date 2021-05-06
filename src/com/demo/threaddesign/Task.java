package com.demo.threaddesign;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 任务对象
 * @date 2021/4/25 16:59
 * @see
 */
public class Task {

    private int id;
    private String name;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
