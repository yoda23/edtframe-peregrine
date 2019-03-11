package cc.edt.frame;

import cc.edt.frame.common.controller.requestbody.HttpServletRequestReplacedFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;


/**
 * 启动器
 *
 * @author 刘钢
 * @date 2018/8/8 20:38
 */

@SpringBootApplication(scanBasePackages = {"cc.edt.frame.**"})
@MapperScan({"cc.edt.frame.api.dao"})
@EnableTransactionManagement
public class WebApiApplication extends SpringBootServletInitializer {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 2-1 处理中文乱码问题
        List<MediaType> fastMediaTypes = Lists.newArrayList();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        // 4、将convert添加到converters中
        return new HttpMessageConverters(
                (HttpMessageConverter<?>) fastConverter);
    }

    @Bean
    public FilterRegistrationBean httpServletRequestReplacedRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestReplacedFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("httpServletRequestReplacedFilter");
        registration.setOrder(1);
        return registration;
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(WebApiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
    }

}
