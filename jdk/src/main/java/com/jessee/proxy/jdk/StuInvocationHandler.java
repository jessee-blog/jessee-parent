package com.jessee.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by helo on 2018/12/9.
 */
public class StuInvocationHandler<T> implements InvocationHandler {
    //invocationHandler���еı��������
    T target;

    public StuInvocationHandler(T target) {
        this.target = target;
    }

    /**
     * proxy:����̬�������
     * method����������ִ�еķ���
     * args���������Ŀ�귽��ʱ�����ʵ��
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("����ִ��" +method.getName() + "����");
        //��������в����ⷽ��,����÷�����ʱ
        MonitorUtil.start();
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }
}