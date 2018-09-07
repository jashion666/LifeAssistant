package com.assistant.controller.user;

import com.assistant.entity.test.TestServiceEntity;
import com.assistant.entity.user.UserEntity;
import com.assistant.jedis.JedisClient;
import com.assistant.service.TestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
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
        }catch(ExcessiveAttemptsException e){
            LOG.error("登录次数超过五次，请10分钟再试");
        }

        return null;
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

}