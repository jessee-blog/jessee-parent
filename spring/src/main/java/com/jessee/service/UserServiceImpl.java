package com.jessee.service;

import com.jessee.dao.UserMapper;
import com.jessee.framwork.annotition.HPAutowire;
import com.jessee.framwork.annotition.HPService;

/**
 * Created by jessee on 2018/12/15.
 */
@HPService
public class UserServiceImpl implements UserService {

    @HPAutowire
    private UserMapper userMapper;

    @Override
    public Integer selectUserCount() {
        userMapper.selectUserById();
        return 1;
    }
}
