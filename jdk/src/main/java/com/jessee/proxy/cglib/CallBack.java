package com.jessee.proxy.cglib;

public class CallBack {
    private String code;

    public CallBack() {
        System.out.println("123" + 789 );
    }

    public CallBack(String code) {
        this.code = code;
    }

    public String doCallBack(Integer money){
        System.out.println("123" + money );
        return "this money is" + money;
    }
}
