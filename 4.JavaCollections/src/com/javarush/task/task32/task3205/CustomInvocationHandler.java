package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Basillio on 03.11.2018.
 */
public class CustomInvocationHandler implements InvocationHandler {
    SomeInterfaceWithMethods obj;

    public CustomInvocationHandler(SomeInterfaceWithMethods siwm){
        obj = siwm;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " in");
        Object result = method.invoke(obj, args);
        System.out.println(method.getName() + " out");
        return result;
    }
}
