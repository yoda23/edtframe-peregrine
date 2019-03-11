package cc.edt.frame.quartz;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cc.edt.frame.quartz.service.QuartzInitlizeService;

/**
 * 定时器启动类
 *
 * @author 刘钢
 * @date 2018/9/14 10:59
 */
@Component
@Order(1)
public class QuartzStarter implements CommandLineRunner {
    @Resource
    private QuartzInitlizeService quartzInitlizeService;

    @Override
    public void run(String... args) {
        quartzInitlizeService.initlize();
    }
}
