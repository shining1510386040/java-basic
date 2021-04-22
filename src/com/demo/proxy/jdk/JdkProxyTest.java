package com.demo.proxy.jdk;

import com.demo.proxy.Persion;
import com.demo.proxy.Student;

import java.lang.reflect.Proxy;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description jdk动态代理 测试：
 * 只能为类生成代理
 * @date 2021/2/10 15:28
 * @see
 */
public class JdkProxyTest {

    public static void main(String[] args) {

        Student student = new Student();

        MyInvocationHandler proxy = new MyInvocationHandler(student);

        Persion persion = (Persion) proxy.getProxy();

        persion.speak();
        persion.walk();

    }
}
