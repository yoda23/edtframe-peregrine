package cc.edt.frame.quartz.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author 刘钢
 * @date 2018/12/20 10:49
 */
@Component("ApplicationContextUtil")
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(
            @NonNull ApplicationContext applicationContext)
            throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    private void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException(
                    "applicationContext 未注入,请在applicationContext.xml中定义SpringContextUtil");
        }
    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return (T) applicationContext.getBean(name, clazz);
    }
}
