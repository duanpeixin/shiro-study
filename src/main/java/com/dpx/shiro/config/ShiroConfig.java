package com.dpx.shiro.config;

import com.dpx.shiro.handle.CustomerRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author:
 * @date: 2020-04-16 11:15
 */
@Configuration
public class ShiroConfig {

    /**
     * 该配置可解决shiro注解无法被映射问题
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 将自己的验证方式加入容器
     *
     * @param
     * @return: com.dpx.shiro.handle.CustomerRealm
     * @author:
     * @date: 2020/4/16
     */
    @Bean
    public CustomerRealm myShiroRealm() {
        CustomerRealm customRealm = new CustomerRealm();
        return customRealm;
    }

    /**
     * 权限管理，核心，配置主要是Realm的管理认证
     *
     * @param
     * @return: org.apache.shiro.mgt.SecurityManager
     * @author:
     * @date: 2020/4/16
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager
     * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @author:
     * @date: 2020/4/16
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * anon: 无需认证（登录）可以访问
         * authc: 必须认证才可以访问
         * user: 如果使用rememberMe的功能可以直接访问
         * perms： 该资源必须得到资源权限才可以访问
         * role: 该资源必须得到角色权限才可以访问
         */
        map.put("/login", "anon");
        map.put("/add", "authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 注解访问授权动态拦截，不然不会执行doGetAuthenticationInfo
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
