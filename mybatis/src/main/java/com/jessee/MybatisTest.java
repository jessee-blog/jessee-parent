package com.jessee;

import com.jessee.framwork.HPSession;
import org.junit.Test;

/**
 * Created by jessee on 2018/12/16.
 */
public class MybatisTest {
    @Test
    public void test() {
        HPSession hpSession = new HPSession();
        UserMapper userMapper = (UserMapper) hpSession.getMapper(UserMapper.class);
        userMapper.selectUserById(1);
        System.out.println("success");
    }
}
