package com.jessee;

/**
 * Created by jessee on 2018/12/9.
 */
public class StudentsProxy implements Person {
    private Student stu;

    public StudentsProxy(Person stu) {
        if (stu.getClass() == Student.class) {
            this.stu = (Student) stu;
        }
    }

    public void giveMoney() {
        System.out.println("代理交费");
        stu.giveMoney();
    }

}
