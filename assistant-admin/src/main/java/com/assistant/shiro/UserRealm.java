package com.assistant.shiro;

import com.assistant.commom.UserConstant;
import com.assistant.entity.user.UserEntity;
import com.assistant.service.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * shiro realm
 *
 * @author ：会写代码的厨师.
 *         时间：2018-08-14.
 */
public class UserRealm extends AuthorizingRealm {

    /**
     * 用户接口
     */
    @Autowired
    UserService userService;


    /**
     * 角色授权
     *
     * @param principalCollection principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 角色认证
     * 根据用户名匹配信息，将取出的password传入shiro进行认证
     *
     * @param token token
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();

        //依据用户名查询用户信息
        UserEntity userInfo = userService.findByUsername(username);

        //该用户不存在
        if (userInfo == null) {
            throw new UnknownAccountException();
        }

        //该用户被锁定
        if (UserConstant.USER_LOCKED_STATE.equals(userInfo.getUserState())) {
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), getName());
    }
}
