package cc.edt.frame;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

/**
 * 单元测试基础类
 *
 * @author 刘钢
 * @date 2018/9/21 15:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebTestApplication.class)
@WebAppConfiguration
@Slf4j
public class BaseTest {
    @Before
    public void init() {
        log.debug("-----------------开始测试-----------------");
    }

    @After
    public void after() {
        log.debug("-----------------测试结束-----------------");
    }
}
