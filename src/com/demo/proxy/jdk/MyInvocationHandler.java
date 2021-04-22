package com.demo.proxy.jdk;

import com.demo.proxy.Persion;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 代理增强
 * @date 2021/2/10 15:40
 * @see
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 目标代理类：
     * use a
     */
    private Object target;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("proxy=====>>>before:" + method);
        method.invoke(proxy, args);
        System.out.println("proxy======>>after:" + method);

        return proxy;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 生成代理类
     * @date 2021/2/10 15:48
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
