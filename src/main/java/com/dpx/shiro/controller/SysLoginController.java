package com.dpx.shiro.controller;

import com.dpx.shiro.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: 
 * @date: 2020-04-16 11:27
 */
@RestController
public class SysLoginController {

    @RequestMapping(value = "/login")
    public String login(@RequestBody UserInfo userInfo) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken passwordToken = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getPassword());
        try {
            subject.login(passwordToken);
        } catch (AuthenticationException e) {
            return "账号密码错误";
        } catch (AuthorizationException e) {
            return "没有权限";
        }
        return "登录成功";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping(value = "/add")
    public String add() {
        return "添加信息";
    }
}
