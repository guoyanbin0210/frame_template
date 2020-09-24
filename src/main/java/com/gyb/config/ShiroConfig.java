package com.gyb.config;


import com.gyb.base.shrio.MyHashedCredentialsMatcher;
import com.gyb.base.shrio.MySessionManager;
import com.gyb.base.shrio.MyShiroRealm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Configuration
public class ShiroConfig {
    private final Logger Logger = LogManager.getLogger(getClass());

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
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

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");

        // 配置静态资源
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");

        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/ajaxLogin", "anon");

        filterChainDefinitionMap.put("/base/doLogin.do", "anon");
        filterChainDefinitionMap.put("/base/doLogout.do", "anon");
        filterChainDefinitionMap.put("/base/unLogin.do", "anon");
        filterChainDefinitionMap.put("/html/login.html", "anon");
        filterChainDefinitionMap.put("/htmlm/**", "anon");

//        filterChainDefinitionMap.put("/", "anon");

        //用户注册
        filterChainDefinitionMap.put("/*/**", "anon");
        filterChainDefinitionMap.put("/api/UserMain/create.do", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/api_p/**", "anon");

        filterChainDefinitionMap.put("/ue/**", "anon");
        filterChainDefinitionMap.put("/swagger**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/v2/api-docs-ext", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html#!/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");

        filterChainDefinitionMap.put("/uploads/**", "anon");
        filterChainDefinitionMap.put("/upload/audio/**", "anon");
        filterChainDefinitionMap.put("/upload/video/**", "anon");
        filterChainDefinitionMap.put("/upload/application/**", "anon");
        filterChainDefinitionMap.put("/download/**", "anon");
        filterChainDefinitionMap.put("/wx/**", "anon");
        filterChainDefinitionMap.put("/MP_verify_pzo7Gi2YRdxupVnw.txt", "anon");
        filterChainDefinitionMap.put("/html/wx/**", "anon");
        filterChainDefinitionMap.put("/html/anon/**", "anon");
        filterChainDefinitionMap.put("/Excel/**", "anon");

        filterChainDefinitionMap.put("/**", "authc");
//        filterChainDefinitionMap.put("/**", "anon");
        shiroFilterFactoryBean.setLoginUrl("/base/unLogin");//这里
        // 登录成功后要跳转的链接 这里通过前端控制
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     *
     * @return
     */
    @Bean
    public MyHashedCredentialsMatcher hashedCredentialsMatcher() {
        MyHashedCredentialsMatcher hashedCredentialsMatcher = new MyHashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
//        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();

        //全局会话超时时间（单位毫秒），
        mySessionManager.setGlobalSessionTimeout(3 * 60 * 60 * 1000);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        mySessionManager.setSessionValidationInterval(1 * 60 * 60 * 1000);
        //取消url 后面的 JSESSIONID
        mySessionManager.setSessionIdUrlRewritingEnabled(false);
//        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }


/*
    @Value("${spring.redis.shiro.host}")
    private String host;
    @Value("${spring.redis.shiro.port}")
    private int port;
    @Value("${spring.redis.shiro.timeout}")
    private int timeout;
    @Value("${spring.redis.shiro.password}")
    private String password;


    */
/**
 * 配置shiro redisManager
 * <p>
 * 使用的是shiro-redis开源插件
 *
 * @return
 *//*

    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
//        redisManager.setPort(port);
//        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    */
/**
 * cacheManager 缓存 redis实现
 * <p>
 * 使用的是shiro-redis开源插件
 *
 * @return
 *//*

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    */
/**
 * RedisSessionDAO shiro sessionDao层的实现 通过redis
 * <p>
 * 使用的是shiro-redis开源插件
 *//*

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
*/


    //    /**
//     * 注册全局异常处理
//     * @return
//     */
//    @Bean(name = "exceptionHandler")
//    public HandlerExceptionResolver handlerExceptionResolver() {
//        return new MyExceptionHandler();
//    }
//    @Bean
//    public SimpleMappingExceptionResolver
//    simpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
//        Properties mappings = new Properties();
//        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
//        mappings.setProperty("UnauthorizedException", "/user/403");
//        r.setExceptionMappings(mappings);  // None by default
//        r.setDefaultErrorView("error");    // No default
//        r.setExceptionAttribute("exception");     // Default is "exception"
//        //r.setWarnLogCategory("example.MvcLogger");     // No default
//        return r;
//    }

}
