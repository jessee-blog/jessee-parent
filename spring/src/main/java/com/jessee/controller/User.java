package com.jessee.controller;

import com.jessee.entity.UserDTO;
import com.jessee.framwork.annotition.HPController;
import com.jessee.framwork.annotition.HPRequestMapping;
import com.jessee.framwork.annotition.HPRequestParam;
import com.jessee.framwork.annotition.HPResponseBody;

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

    @HPRequestMapping("/selectUserById")
    @HPResponseBody
    public UserDTO selectUserById(@HPRequestParam("id") Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(20);
        userDTO.setName("zs");
        return userDTO;
    }
}
