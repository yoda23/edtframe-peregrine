package cc.edt.frame.common.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cc.edt.frame.common.constant.CommonDictionary;

/**
 * 资源配置类
 *
 * @author 刘钢
 * @date 2018/6/19 19:48
 */
@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {
    @Resource
    private CommonDictionary commonDictionary;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler(
                "/" + commonDictionary.getUploadContextPath() + "/**")
                .addResourceLocations(
                        "file:" + commonDictionary.getUploadPathDisk());
    }

}
