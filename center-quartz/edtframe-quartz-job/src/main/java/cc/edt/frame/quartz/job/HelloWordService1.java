package cc.edt.frame.quartz.job;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 刘钢
 * @date 2018/12/20 15:34
 */
@Service("helloWordService1")
@Slf4j
public class HelloWordService1 {

    public void sayHello() {
        log.info(
                "--------------------HELLO WORLD1------------------------------");
    }
}
