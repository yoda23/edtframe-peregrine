package cc.edt.frame.shiro;

import cc.edt.frame.common.constant.CommonDictionary;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * shiro的spring监听器
 *
 * @author 刘钢
 * @date 2018/9/13 17:09
 */
@Component
public class ShiroSpringEventListener {


    @Bean("authorityRealm")
    public MyRealm getUserRealm() {
        return new MyRealm();
    }

    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(getUserRealm());
        manager.setCacheManager(new MemoryConstrainedCacheManager());
        return manager;
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        DefaultWebSecurityManager manager = (DefaultWebSecurityManager) context
                .getBean("securityManager");
        AuthorizingRealm realm = (AuthorizingRealm) context
                .getBean("authorityRealm");
        // realm.setCredentialsMatcher(new CustomCredentialsMatcher());
        manager.setRealm(realm);
    }
}
