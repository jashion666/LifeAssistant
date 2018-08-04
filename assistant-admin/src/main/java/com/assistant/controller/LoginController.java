package com.assistant.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.assistant.entity.TestAdminEntity;
import com.assistant.entity.test.TestServiceEntity;
import com.assistant.jedis.JedisClient;
import com.assistant.service.TestService;
import com.assistant.service.test.read.TestReadService;
import com.assistant.service.test.write.TestWriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/7/26.
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Reference(check = false)
    TestReadService testReadService;

    @Reference(check = false)
    TestWriteService testWriteService;

    @Autowired
    TestService testService;

    @Autowired
    JedisClient redisManager;

    @RequestMapping("/login")
    @ResponseBody
    public String login() {
        redisManager.set("1", "redis测试：字符串");
        TestAdminEntity info = testService.getTestInfo();
        TestServiceEntity serviceUserInfo = testReadService.findUserInfo();
        LOG.info(serviceUserInfo.getUserName());
        return "admin的结果：" + info.toString() + "   service 的结果：" + serviceUserInfo.toString();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        LOG.debug(redisManager.get("1"));
        testWriteService.saveUserInfo(new TestServiceEntity());
        return "";
    }


}
