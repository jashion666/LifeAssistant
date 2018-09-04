package com.assistant.controller.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.assistant.entity.TestAdminEntity;
import com.assistant.entity.test.TestServiceEntity;
import com.assistant.service.test.read.TestReadService;
import com.assistant.service.test.write.TestWriteService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/9/4.
 */
@Controller
@RequestMapping("dubbo")
public class DubboTestController {

    @Reference(check = false)
    TestReadService testReadService;

    @Reference(check = false)
    TestWriteService testWriteService;

    @RequestMapping("/test")
    @ResponseBody
    public TestServiceEntity login() {
        return testReadService.findUserInfo();
    }
}
