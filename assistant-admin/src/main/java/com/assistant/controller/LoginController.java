package com.assistant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.assistant.entity.TestEntity;
import com.assistant.service.IUserService;
import com.assistant.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/7/26.
 */
@Controller
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class);

    @Reference
    IUserService userService;

    @Autowired
    TestService testService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        TestEntity info = testService.getTestInfo();
        LOG.info(info.getUserName());
        return userService.getUserInfo(1);
    }
}
