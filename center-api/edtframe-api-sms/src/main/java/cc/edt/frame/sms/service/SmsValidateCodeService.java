package cc.edt.frame.sms.service;

import cc.edt.frame.common.result.ActionResult;

/**
 * 短信验证码
 *
 * @author 刘钢
 * @date 2017/12/18 13:59
 */
public interface SmsValidateCodeService {

    /**
     * 生成短信验证码
     *
     * @param phone phone
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * @date 2017-05-18 11:54
     */

    String getSmsValidateCode(String phone);

    /**
     * 验证短信验证码
     *
     * @param phone phone
     * @param code  code
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * @date 2017-05-18 11:55Z
     */

    ActionResult checkSmsValidateCode(String phone, String code);

}
