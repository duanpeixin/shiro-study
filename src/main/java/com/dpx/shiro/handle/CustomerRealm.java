package com.dpx.shiro.handle;

import com.dpx.shiro.entity.AuthorityInfo;
import com.dpx.shiro.entity.RoleInfo;
import com.dpx.shiro.entity.UserInfo;
import com.dpx.shiro.service.ISysLoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: duanpeixin
 * @date: 2020-04-16 11:07
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private ISysLoginService iSysLoginService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获得用户登录名
        UserInfo userInfo = iSysLoginService.getUserInfo(principalCollection.getPrimaryPrincipal().toString());
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (RoleInfo roleInfo : userInfo.getRoleInfoList()) {
            simpleAuthorizationInfo.addRole(roleInfo.getRoleName());
            for (AuthorityInfo authorityInfo : roleInfo.getAuthorityInfoList()) {
                simpleAuthorizationInfo.addStringPermission(authorityInfo.getAuthorityName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证 用户登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 加这一步的目的是在请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        UserInfo userInfo = iSysLoginService.getUserInfo(authenticationToken.getPrincipal().toString());
        if (userInfo == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(userInfo.getUserName(), userInfo.getPassword(), getName());
        }
    }
}
