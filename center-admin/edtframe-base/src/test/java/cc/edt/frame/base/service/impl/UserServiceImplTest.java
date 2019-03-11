package cc.edt.frame.base.service.impl;

import cc.edt.frame.BaseTest;
import cc.edt.frame.base.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author 刘钢
 * @date 2019/1/14 15:31
 */
public class UserServiceImplTest extends BaseTest {
    @Resource
    private UserService userService;

    @Test
    public void getUserById() {
        System.out.println(userService.getUserById("123"));
    }
}