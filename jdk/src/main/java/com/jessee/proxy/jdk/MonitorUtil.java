package com.jessee.proxy.jdk;

/**
 * Created by helo on 2018/12/9.
 */
public class MonitorUtil {

    private static ThreadLocal<Long> tl = new ThreadLocal<Long>();

    public static void start() {
        tl.set(System.currentTimeMillis());
    }

    //����ʱ��ӡ��ʱ
    public static void finish(String methodName) {
        long finishTime = System.currentTimeMillis();
        System.out.println(methodName + "������ʱ" + (finishTime - tl.get()) + "ms");
    }
}