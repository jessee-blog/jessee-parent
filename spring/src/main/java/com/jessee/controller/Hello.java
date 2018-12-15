package com.jessee.controller;

import ch.qos.logback.classic.Logger;
import com.jessee.framwork.annotition.HPAutowire;
import com.jessee.framwork.annotition.HPController;
import com.jessee.framwork.annotition.HPRequestMapping;
import com.jessee.framwork.annotition.HPRequestParam;
import com.jessee.service.UserService;
import org.slf4j.LoggerFactory;

/**
 * Created by jessee on 2018/12/15.
 */
@HPController
@HPRequestMapping("/hello")
public class Hello {

    Logger logger = (Logger) LoggerFactory.getLogger(Hello.class);

    @HPAutowire
    private UserService userService;

    @HPRequestMapping("/selectCount")
    public String selectCount(@HPRequestParam("id") Integer integer) {
        logger.info("do selectCount:{}", integer);
        Integer num = userService.selectUserCount();
        logger.info("num:{}", num);

        return "sucess:id=" + integer;
    }
}
