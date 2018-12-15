package com.jessee.proxy.cglib;

public class Test {

    @org.junit.Test
    public void testInvoke2(){
        /*BookFacadeImpl1 bookFacade=new BookFacadeImpl1();
        BookFacadeCglib  cglib=new BookFacadeCglib();
        BookFacadeImpl1 bookCglib=(BookFacadeImpl1)cglib.getInstance(bookFacade);
        bookCglib.addBook();*/


        CallBack callBack = new CallBack("alipay");
        BookFacadeCglib  cglib2=new BookFacadeCglib();
        CallBack callBack2=(CallBack)cglib2.getInstance(callBack);
        callBack2.doCallBack(600);
        System.out.println( callBack2.doCallBack(600));
    }
}
