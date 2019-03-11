package cc.edt.frame.location.service.impl;

import cc.edt.frame.admin.dao.base.PhoneLocationDao;
import cc.edt.frame.admin.dao.base.PhoneOperatorDao;
import cc.edt.frame.location.dto.PhoneLocationInfo;
import cc.edt.frame.location.service.PhoneLocationService;
import cc.edt.frame.model.entity.location.PhoneLocation;
import cc.edt.frame.model.entity.operator.PhoneOperator;
import cc.edt.iceutils3.baidu.mobile.IceBaiDuMobileUtils;
import cc.edt.iceutils3.baidu.mobile.bean.MobileResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * 手机号码归属地
 *
 * @author 刘钢
 * @date 2018/1/12 14:44
 */
@Service("PhoneLocationBaiDu")
@Transactional(rollbackFor = Exception.class)
public class PhoneLocationServiceImplBaiDu implements PhoneLocationService {
    /**
     * 号码最少位数
     */
    private final static int MOBILE_LESS_LENGTH = 7;
    @Resource
    private PhoneLocationDao phoneLocationDao;
    @Resource
    private PhoneOperatorDao phoneOperatorDao;

    @Override
    public PhoneLocationInfo getPhoneLocationByHeader(String header) {
        PhoneLocationInfo phoneLocationInfo = new PhoneLocationInfo();
        if (StringUtils.isBlank(header)
                || StringUtils.length(header) < MOBILE_LESS_LENGTH) {
            return phoneLocationInfo;
        }
        // 根据百度归属地查询
        try {
            MobileResult mobileResult = IceBaiDuMobileUtils
                    .mobileLocation(header);
            if (mobileResult.getMobileResponse().getMobileInfo() != null) {
                phoneLocationInfo.setProvince(mobileResult.getMobileResponse()
                        .getMobileInfo().getMobileDetail().getProvince());
                phoneLocationInfo.setCity(mobileResult.getMobileResponse()
                        .getMobileInfo().getMobileDetail().getListMobileArea()
                        .get(0).getCity());
                phoneLocationInfo.setOperator(mobileResult.getMobileResponse()
                        .getMobileInfo().getMobileDetail().getOperator());
                return phoneLocationInfo;
            }
        } catch (NoSuchAlgorithmException | IOException
                | KeyManagementException e) {
            e.printStackTrace();
        }
        // 根据本地归属地信息
        PhoneLocation phoneLocation = phoneLocationDao.getPhoneLocationByHeader(
                StringUtils.substring(header, 0, MOBILE_LESS_LENGTH));
        if (phoneLocation != null) {
            phoneLocationInfo.setProvince(phoneLocation.getProvince());
            phoneLocationInfo.setCity(phoneLocation.getCity());
        }
        // 查询运营商
        PhoneOperator phoneOperator = phoneOperatorDao.getPhoneOperatorByPrefix(
                StringUtils.substring(header, 0, MOBILE_LESS_LENGTH));
        if (phoneOperator != null) {
            phoneLocationInfo.setOperator(phoneOperator.getOperatorName());
        }
        return phoneLocationInfo;
    }

}
