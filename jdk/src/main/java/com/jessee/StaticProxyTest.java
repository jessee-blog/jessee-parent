package com.jessee;

/**
 * Created by jessee on 2018/12/9.
 */
public class StaticProxyTest {
    public static void main(String[] args) {

        Person zhangsan = new Student("张三");

        Person monitor = new StudentsProxy(zhangsan);

        monitor.giveMoney();
    }
}
