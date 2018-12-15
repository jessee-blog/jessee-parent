package com.jessee.proxy;

/**
 * Created by helo on 2018/12/9.
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        //�������ѧ�����������İ���Ͻ��д������monitor���೤�����
        Person zhangsan = new Student("����");

        //���ɴ�����󣬲������������������
        Person monitor = new StudentsProxy(zhangsan);

        //�೤�����Ͻ����
        monitor.giveMoney();
    }
}
