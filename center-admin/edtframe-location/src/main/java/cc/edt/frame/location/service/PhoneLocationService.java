package cc.edt.frame.location.service;


import cc.edt.frame.location.dto.PhoneLocationInfo;

/**
 * 手机号码归属地
 *
 * @author 刘钢
 * @date 2017/12/18 13:55
 */
public interface PhoneLocationService {
    /**
     * 根据手机号前七位查询号码归属地
     *
     * @param header head
     * @return com.edt.entity.PhoneLocation
     * @author 刘钢
     * @date 2017-05-18 11:42
     */

    PhoneLocationInfo getPhoneLocationByHeader(String header);

}
