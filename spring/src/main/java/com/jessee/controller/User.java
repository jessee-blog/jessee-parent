package com.jessee.controller;

import com.jessee.framwork.annotition.HPController;
import com.jessee.framwork.annotition.HPRequestMapping;
import com.jessee.framwork.annotition.HPRequestParam;

/**
 * Created by jessee on 2018/12/15.
 */
@HPController
@HPRequestMapping("/user")
public class User {

    @HPRequestMapping("/saveUser")
    public String saveUser(@HPRequestParam("user") String name) {
        return "saveUser success !user:" + name;
    }
}
