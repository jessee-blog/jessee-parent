package com.jessee;

/**
 * Created by jessee on 2018/12/9.
 */
public class Student implements Person {
    private String name;
    public Student(String name) {
        this.name = name;
    }

    public void giveMoney() {
        System.out.println(name + "交费50");
    }
}
