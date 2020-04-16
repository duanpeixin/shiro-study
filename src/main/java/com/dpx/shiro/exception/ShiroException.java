package com.dpx.shiro.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author:
 * @date: 2020-04-16 14:52
 */
@ControllerAdvice
public class ShiroException {

    @ExceptionHandler
    @ResponseBody
    public String noAuthorityHandler(AuthorizationException e){
        return "没有该权限";
    }
}
