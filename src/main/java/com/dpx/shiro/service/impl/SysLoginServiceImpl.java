package com.dpx.shiro.service.impl;

import com.dpx.shiro.entity.AuthorityInfo;
import com.dpx.shiro.entity.RoleInfo;
import com.dpx.shiro.entity.UserInfo;
import com.dpx.shiro.service.ISysLoginService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:
 * @date: 2020-04-16 10:45
 */
@Service
public class SysLoginServiceImpl implements ISysLoginService {
    @Override
    public UserInfo getUserInfo(String userName) {
        return simulationGetInfo(userName);
    }

    /**
     * 模拟数据库获得用户信息
     *
     * @param userName
     * @return: com.dpx.shiro.entity.UserInfo
     * @date: 2020/4/16
     */
    private UserInfo simulationGetInfo(String userName) {
        // 设置两个权限 一个add 一个query
        AuthorityInfo authorityInfoAdd = new AuthorityInfo(1,"add");
        AuthorityInfo authorityInfoQuery = new AuthorityInfo(2,"query");
        // 查询权限集合
        List<AuthorityInfo> queryList = Lists.newArrayList();
        queryList.add(authorityInfoQuery);
        // 所有权限集合
        List<AuthorityInfo> allList = Lists.newArrayList();
        allList.add(authorityInfoAdd);
        allList.add(authorityInfoQuery);

        // 设置两个角色 一个管理员 一个普通用户
        RoleInfo adminRole = new RoleInfo(1,"admin",allList);
        RoleInfo ordinaryRole = new RoleInfo(2,"ordinary",queryList);

        // 设置两个用户
        UserInfo adminUser = new UserInfo(1,"admin","123456", Collections.singletonList(adminRole));
        UserInfo zhangsanUser = new UserInfo(2,"zhangsan","123456",Collections.singletonList(ordinaryRole));

        Map<String,UserInfo> map = Maps.newHashMap();
        map.put(adminUser.getUserName(),adminUser);
        map.put(zhangsanUser.getUserName(),zhangsanUser);

        return map.get(userName);
    }
}
