package com.jessee.framwork.context;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jessee on 2018/12/15.
 */
public class HPHandler {
    private Object controller;
    private Method method;
    private Map<String, Integer> paramsMap = new HashMap<>();

    public HPHandler(Object controller, Method method, Map<String, Integer> paramsMap) {
        this.controller = controller;
        this.method = method;
        this.paramsMap = paramsMap;
    }

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }

    public Map<String, Integer> getParamsMap() {
        return paramsMap;
    }
}