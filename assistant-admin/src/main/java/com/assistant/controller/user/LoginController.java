package com.assistant.controller.user;

import com.assistant.entity.user.UserEntity;
import com.assistant.result.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * log
     */
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录首页接口
     *
     * @return login.html
     */
    @RequestMapping("/index")
    public String index() {
        return "login";
    }

    /**
     * 执行登录
     *
     * @param user 用户信息
     * @return JsonResult
     */
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(UserEntity user) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        token.setRememberMe("on".equals(user.getRememberMe()));

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return JsonResult.failedResult("该用户不存在。");
        } catch (LockedAccountException e) {
            return JsonResult.failedResult("该用户被冻结。");
        } catch (IncorrectCredentialsException e) {
            return JsonResult.failedResult("密码错误。");
        } catch (ExcessiveAttemptsException e) {
            return JsonResult.failedResult("登陆次数超过五次，该账号已被锁定。请10分钟后再试。");
        }

        return JsonResult.successResult("登陆成功");
    }

    /**
     * 登录成功后的首页
     *
     * @return 首页
     */
    @RequestMapping("/welcome")
    public String welcome() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        UserEntity sessionUser = (UserEntity) session.getAttribute("shiroUser");
        return "home/welcome";
    }

    /**
     * 用户登出
     *
     * @return 首页
     */
    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

}
