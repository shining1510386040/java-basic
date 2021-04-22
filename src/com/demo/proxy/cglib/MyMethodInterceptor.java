package com.demo.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 方法增强
 * @date 2021/2/10 15:34
 * @see
 */
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before: " + method);
        // 目标方法执行
        Object object = methodProxy.invokeSuper(obj, args);
//        methodProxy.invoke();
        System.out.println("After: " + method);
        return object;
    }
}
