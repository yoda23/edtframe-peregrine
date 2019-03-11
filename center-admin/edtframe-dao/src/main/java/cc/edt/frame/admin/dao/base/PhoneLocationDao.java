package cc.edt.frame.admin.dao.base;


import cc.edt.frame.model.entity.location.PhoneLocation;

/**
 * 号码归属地信息
 *
 * @author 刘钢
 * @date 2018/1/12 13:36
 */
public interface PhoneLocationDao {

    /**
     * 根据手机号前七位查询号码归属地
     *
     * @param header header
     * @return com.edt.entity.PhoneLocation
     * @author 刘钢
     * @date 2017/5/17 22:43
     */
    PhoneLocation getPhoneLocationByHeader(String header);

}
