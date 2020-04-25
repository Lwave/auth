package com.example.common.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import com.example.common.cache.ShiroCacheManager;
import com.example.common.session.ShiroSessionDao;
import com.example.sys.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 加密配置
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(1024);
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }


    /**
     * 自定义会话sessionDao
     * /**
     * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
     * MemorySessionDAO 直接在内存中进行会话维护
     * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     *
     * @return
     */
    @Bean
    public SessionDAO sessionDAO() {
        ShiroSessionDao sessionDao = new ShiroSessionDao(shiroCacheManager());
        return sessionDao;
    }


    /**
     * 配置缓存管理器
     *
     * @return
     */
    @Bean(name = "shiroCacheManager")
    public CacheManager shiroCacheManager() {
        return new ShiroCacheManager(redisTemplate);
    }

    /**
     * 配置会话管理器
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        //web环境  非httpsession会话管理器
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }

    /**
     * cookie对象;
     * @return
     */
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间30天,单位秒;
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     * @return
     */
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // cookieRememberMeManager.setCipherKey用来设置加密的Key,参数类型byte[],字节数组长度要求16
        // cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        cookieRememberMeManager.setCipherKey("ZHANGXIAOHEI_CAT".getBytes());
        return cookieRememberMeManager;
    }

    /**
     * 装配 自定义Realm
     *
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        // 告诉realm,使用credentialsMatcher加密算法类来验证密文
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        shiroRealm.setCachingEnabled(true);
        return shiroRealm;
    }

    /**
     * 配置安全管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        // shrio自己维护会话  默认不使用httpsession
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 注入自定义Realm
        securityManager.setRealm(shiroRealm());
        //注入缓存管理器
        securityManager.setCacheManager(shiroCacheManager());

        //注入rememberMeManager管理器
         securityManager.setRememberMeManager(rememberMeManager());

        //注入会话管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }


    /**
     * 配置权限权限过滤器
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        // 注入安全管理器
        filter.setSecurityManager(securityManager());
        // 未认证的跳转地址
        filter.setLoginUrl("/login");
        Map<String, String> chain = new LinkedHashMap<>();
        chain.put("/login", "anon"); // 登录链接不拦截
        chain.put("/css/**", "anon");
        chain.put("/img/**", "anon");
        chain.put("/js/**", "anon");
        chain.put("/lib/**", "anon");

        //配置记住我或认证通过可以访问的地址
        chain.put("/**", "user");
        filter.setFilterChainDefinitionMap(chain);
        return filter;
    }


    /**
     * 启用Shiro注解
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        // 注入安全管理器
        advisor.setSecurityManager(securityManager());
        return advisor;
    }


    /*
     *
     * 启用shiro thymeleaf标签支持
     *
     * @return*/
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
