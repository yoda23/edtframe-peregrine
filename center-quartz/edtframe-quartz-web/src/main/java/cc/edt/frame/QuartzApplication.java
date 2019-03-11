package cc.edt.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动服务
 *
 * @author 刘钢
 * @date 2018/8/8 20:38
 */

@SpringBootApplication(scanBasePackages = {"cc.edt.frame.**"})
@MapperScan({"cc.edt.frame.admin.dao"})
@EnableTransactionManagement
public class QuartzApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(QuartzApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

}
