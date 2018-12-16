package com.jessee.orm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jessee on 2018/12/16.
 */
public class OrmInvocation implements InvocationHandler {

    private Object target;

    public OrmInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        return result;
    }
}
