package cc.edt.frame.interceptor.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cc.edt.frame.interceptor.ErrorRequestInterceptor;

/**
 * 拦截器配置类
 *
 * @author 刘钢
 * @date 2018/9/20 10:44
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Resource
    private ErrorRequestInterceptor errorRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errorRequestInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }
}
