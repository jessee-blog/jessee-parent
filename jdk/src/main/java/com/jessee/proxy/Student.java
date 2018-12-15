package com.jessee.proxy;

/**
 * Created by helo on 2018/12/9.
 */
public class Student implements Person {
    private String name;
    public Student(String name) {
        this.name = name;
    }

    public void giveMoney() {
        System.out.println(name + "�Ͻ����50Ԫ");
    }
}
