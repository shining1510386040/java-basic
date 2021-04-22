package com.demo.proxy.cglib;

import com.demo.proxy.Student;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description cglib 动态代理测试
 * 可为接口和类生成代理
 * @date 2021/2/10 15:37
 * @see
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        //可以指定 CGLIB 将动态生成的代理类保存至指定的磁盘路径下
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"本地磁盘路径");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class);
        enhancer.setCallback(new MyMethodInterceptor());

        Student student = (Student) enhancer.create();
        student.sayHello();
        student.speak();
        student.walk();
        student.study();
    }
}
