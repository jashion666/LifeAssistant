package com.assistant.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.assistant.entity.TestAdminEntity;
import com.assistant.entity.test.TestServiceEntity;
import com.assistant.entity.user.UserEntity;
import com.assistant.jedis.JedisClient;
import com.assistant.service.TestService;
import com.assistant.service.test.read.TestReadService;
import com.assistant.service.test.write.TestWriteService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
@RequestMapping("/user")
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    TestService testService;

    @Autowired
    JedisClient jedisClient;

    @RequestMapping("/index")
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public TestServiceEntity login(UserEntity user) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            LOG.error("该用户不存在");
        } catch (LockedAccountException e) {
            LOG.error("该用户被冻结");
        }catch(IncorrectCredentialsException e){
            LOG.error("密码错误，数据库密码需要加盐再md5两次");
        }

        return null;
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

}
