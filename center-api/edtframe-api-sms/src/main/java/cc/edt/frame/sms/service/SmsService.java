package cc.edt.frame.sms.service;

import cc.edt.frame.common.result.ActionResult;

/**
 * 短信
 *
 * @author 刘钢
 * @date 2018/12/19 11:35
 */
public interface SmsService {

    /**
     * 发送短信
     *
     * @param phone   phone
     * @param content content
     * @return cc.edt.frame.common.result.ActionResult
     * @author 刘钢
     * @date 2018/12/19 11:36
     */
    ActionResult sendSms(String phone, String content);

}
