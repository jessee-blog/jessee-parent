package com.jessee.framwork;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jessee on 2018/12/16.
 */
public class HPInvocationHandler implements InvocationHandler {

    private HPSession hpSession;
    private Class clazz;

    public HPInvocationHandler(HPSession hpSession, Class clazz) {
        this.hpSession = hpSession;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String key = method.getDeclaringClass().getName() + "." + method.getName();
        System.out.println("key:" + key);
        String sql = hpSession.getSqlMap().get(key);
        Object result = hpSession.selectOne(sql, args[0]);
        return result;
    }
}
