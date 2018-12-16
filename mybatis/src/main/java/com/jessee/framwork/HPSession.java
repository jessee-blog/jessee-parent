package com.jessee.framwork;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jessee on 2018/12/16.
 */
public class HPSession {

    //sql 根据配置获取mapper，将包含注解的方法解析为sql和key存入map
    private Map<String, String> sqlMap = new HashMap<>();

    public HPSession() {
        sqlMap.put("com.jessee.dao.UserMapper.selectUserById", "INSERT INTO `test`.`t_user` (`age`, `name`) VALUES (?, ?)");
    }

    //excutor

    public Object getMapper(Class clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new HPInvocationHandler(this, clazz));
    }

    public Object selectOne(String sql, Object arg) {
        //sql执行
        Object result = new Object();
        System.out.println("sql:" + sql + " arg:" + arg);
        System.out.println("执行完毕、返回结果");
        return result;
    }

    public Map<String, String> getSqlMap() {
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }
}
