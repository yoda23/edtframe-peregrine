package cc.edt.frame.shiro;

import cc.edt.frame.common.constant.CommonDictionary;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 *
 * @author 刘钢
 * @date 2018/6/19 20:49
 */
@Configuration
public class ShiroConfiguration {
    @Resource
    private CommonDictionary commonDictionary;

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager securityManager
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @author 刘钢
     * @date 2018/6/19 21:26
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/timeout");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/timeout");
        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 开放登陆接口
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/logout", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/getValidateCode", "anon");
        filterChainDefinitionMap.put("/timeout", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/wechat/callback/**", "anon");
        filterChainDefinitionMap
                .put("/" + commonDictionary.getUploadContextPath(), "anon");
        // 其余接口一律拦截
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehcacheManager;
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheManager ehCacheCacheManager() {
        return CacheManager.create();
    }
}
