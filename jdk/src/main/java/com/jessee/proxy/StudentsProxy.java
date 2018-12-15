package com.jessee.proxy;

/**
 * Created by helo on 2018/12/9.
 */
public class StudentsProxy implements Person {
    //�������ѧ��
    private Student stu;

    public StudentsProxy(Person stu) {
        // ֻ����ѧ������
        if(stu.getClass() == Student.class) {
            this.stu = (Student)stu;
        }
    }

    //�����Ͻ���ѣ����ñ�����ѧ�����Ͻ������Ϊ
    public void giveMoney() {
        System.out.println("�������ѧϰ�н�����");
        stu.giveMoney();
    }

    //����ģʽ����Ҫ�ľ�����һ�������ӿڣ�Person����һ��������ࣨStudent����һ�������ࣨStudentsProxy��,
    // ��������о������ʵ������Ϊִ�о�����ʵ������������˵��������ģʽ�����ڷ���ʵ�ʶ���ʱ����һ���̶ȵļ���ԣ���Ϊ���ּ���ԣ����Ը��Ӷ�����;��
    // ����ļ���Ծ���ָ��ֱ�ӵ���ʵ�ʶ���ķ�������ô�����ڴ�������оͿ��Լ���һЩ������;��
    // �����������˵������೤�ڰ������Ͻ����֮ǰ��Ҫ�ȷ�ӳһ���������ѧϰ�кܴ������ͨ������ģʽ�����ɾ��ܰ쵽
}
