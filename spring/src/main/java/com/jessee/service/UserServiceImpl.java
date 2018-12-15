package com.jessee.service;

import com.jessee.framwork.annotition.HPService;

/**
 * Created by jessee on 2018/12/15.
 */
@HPService
public class UserServiceImpl implements UserService {

    @Override
    public Integer selectUserCount() {
        return 1;
    }
}
