package cc.edt.frame.api.dao.sms;

import cc.edt.frame.model.entity.sms.SmsValidateCode;

/**
 * 短信验证码
 *
 * @author 刘钢
 * @date 2017/12/18 13:28
 */
public interface SmsValidateCodeDao {
    /**
     * 保存验证码
     *
     * @param smsValidateCode smsValidateCode
     * @author 刘钢 2017/5/17 22:52
     */
    void saveSmsValidateCode(SmsValidateCode smsValidateCode);

    /**
     * 修改验证码
     *
     * @param smsValidateCode smsValidateCode
     * @author 刘钢 2017/5/17 22:53
     */
    void updateSmsValidateCode(SmsValidateCode smsValidateCode);

    /**
     * 根据手机号码查询验证码
     *
     * @param phone phone
     * @return com.edt.entity.ValidateCode
     * @author 刘钢 2017/5/17 22:53
     */
    SmsValidateCode getSmsValidateCodeByPhone(String phone);

}
