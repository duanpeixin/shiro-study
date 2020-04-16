package com.dpx.shiro.service;

import com.dpx.shiro.entity.UserInfo;

/**
 * @description:
 * @author:
 * @date: 2020-04-16 10:45
 */
public interface ISysLoginService {
    /**
     * 获得用户信息
     *
     * @param userName
     * @return: com.dpx.shiro.entity.UserInfo
     * @author:
     * @date: 2020/4/16
     */
    UserInfo getUserInfo(String userName);
}
