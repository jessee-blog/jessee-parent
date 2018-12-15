package com.jessee.framwork.context;

/**
 * Created by jessee on 2018/12/15.
 */
class BeanDefinition {
    private String scope;
    private Object bean;

    public BeanDefinition(String scope, Object bean) {
        this.scope = scope;
        this.bean = bean;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}