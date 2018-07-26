package com.assistant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.assistant.service.IUserService;
import org.apache.log4j.Logger;
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
    private IUserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        System.out.println("Hello World!");
        LOG.info("Hello World!");
        return userService.getUserInfo(1);
    }
}
