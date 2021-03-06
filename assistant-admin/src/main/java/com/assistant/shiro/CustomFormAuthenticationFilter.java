package com.assistant.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 表单过滤器
 *
 * @author ：会写代码的厨师.
 * @date ：2018-08-14.
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {


    /**
     * 创建登陆认证token传值传递给UserRealm的shiro认证
     *
     * @param request  request
     * @param response response
     * @return token
     * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#createToken(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(getUsername(request), getPassword(request), isRememberMe(request),
                getHost(request), true);
        token.setRequest(request);
        return token;
    }

}
