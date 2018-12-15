package com.jessee.jdk;

/**
 * Created by jessee on 2018/12/9.
 */
public class Student implements Person {
    private String name;
    public Student(String name) {
        this.name = name;
    }

    public void giveMoney() {
        try {
            //������Ǯ����һ��ʱ��
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "�Ͻ����50Ԫ");
    }

    public void giveJob() {
        System.out.println(name + "�Ͻ���ҵ");
    }
}