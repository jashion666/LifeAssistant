package com.assistant.filter;

import com.assistant.entity.user.UserEntity;
import com.assistant.service.user.UserService;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author ：会写代码的厨师.
 * @date ：2018/9/30.
 */
public class UserSessionFilter extends AccessControlFilter {

    /**
     * 用户接口
     */
    @Autowired
    UserService userService;

    /**
     * 重写preHandle来配置session, 防止用户选择rememberMe之后下次登录没有session
     *
     * @param request  request
     * @param response response
     * @return boolean
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        // 没有登录
        if (subject == null) {
            return false;
        }

        //从shiro拿出session
        Session session = subject.getSession();
        boolean sessionInvalid = session.getAttribute("shiroUser") == null;
        //如果session为空需要从数据库从新检索
        if (sessionInvalid) {
            String username = (String) subject.getPrincipal();
            //依据用户名查询用户信息
            UserEntity userInfo = userService.findByUsername(username);
            session.setAttribute("shiroUser", userInfo);
        }

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated() || subject.isRemembered();
    }
}
