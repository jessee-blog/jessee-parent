package com.jessee.dao;

import com.jessee.annotition.HPInsert;
import com.jessee.annotition.HPSelect;

/**
 * Created by jessee on 2018/12/16.
 */
public interface UserMapper {
    @HPSelect("select * from t_user ")
    public void selectUserById(Integer id);

    @HPInsert("insert into t_user (name,age) value (#{name},#{age})")
    public void insertUser(String name, Integer age);
}
