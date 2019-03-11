package cc.edt.frame.location.service.impl;

import cc.edt.frame.BaseTest;
import cc.edt.frame.location.dto.PhoneLocationInfo;
import cc.edt.frame.location.service.PhoneLocationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author 刘钢
 * @date 2019/1/15 9:39
 */
@Slf4j
public class PhoneLocationServiceImplBaiDuTest extends BaseTest {
    @Resource(name = "PhoneLocationBaiDu")
    private PhoneLocationService phoneLocationService;

    @Test
    public void getPhoneLocationByHeader() {
        PhoneLocationInfo phoneLocationInfo = phoneLocationService
                .getPhoneLocationByHeader("18646006388");
        System.out.println(phoneLocationInfo);
    }
}