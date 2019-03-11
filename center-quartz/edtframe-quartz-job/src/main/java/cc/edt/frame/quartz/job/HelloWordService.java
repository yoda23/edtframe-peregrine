package cc.edt.frame.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 刘钢
 * @date 2018/12/20 15:34
 */
@Service("helloWordService")
@Slf4j
public class HelloWordService {

    public void sayHello() {
        log.info(
                "--------------------HELLO WORLD------------------------------");
    }
}
