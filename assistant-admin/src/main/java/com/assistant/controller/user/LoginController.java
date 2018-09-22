package com.assistant.controller.user;

import com.assistant.entity.user.UserEntity;
import com.assistant.result.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
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
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return new JsonResult<>(JsonResult.ERROR_CODE, "该用户不存在");
        } catch (LockedAccountException e) {
            return new JsonResult<>(JsonResult.ERROR_CODE, "该用户被冻结");
        } catch (IncorrectCredentialsException e) {
            return new JsonResult<>(JsonResult.ERROR_CODE, "密码错误");
        } catch (ExcessiveAttemptsException e) {
            return new JsonResult<>(JsonResult.ERROR_CODE, "登陆次数超过五次，请10分钟再试");
        }
        return new JsonResult<>(null);
    }

    /**
     * 登录成功后的首页
     *
     * @return 首页
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return "home/welcome";
    }

}
