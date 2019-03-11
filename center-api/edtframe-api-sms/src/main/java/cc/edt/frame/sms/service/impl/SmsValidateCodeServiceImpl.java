package cc.edt.frame.sms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.edt.frame.common.constant.SystemConstant;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.api.dao.sms.SmsValidateCodeDao;
import cc.edt.frame.model.entity.sms.SmsValidateCode;
import cc.edt.frame.sms.service.SmsValidateCodeService;
import cc.edt.iceutils3.date.IceDateOperationUtils;
import cn.hutool.core.util.RandomUtil;

/**
 * 短信验证码
 *
 * @author 刘钢
 * @date 2018/12/19 13:30
 */
@Service
public class SmsValidateCodeServiceImpl implements SmsValidateCodeService {
    @Resource
    private SmsValidateCodeDao smsValidateCodeDao;
    @Resource
    private ActionResultService actionResultService;

    @Override
    public String getSmsValidateCode(String phone) {
        SmsValidateCode smsValidateCodePhone = smsValidateCodeDao
                .getSmsValidateCodeByPhone(phone);
        if (smsValidateCodePhone == null) {
            // 添加验证码
            smsValidateCodePhone = new SmsValidateCode();
            smsValidateCodePhone.setPhone(phone);
            smsValidateCodePhone.setCode(RandomUtil.randomNumbers(6));
            smsValidateCodePhone.setValidTime(new Date());
            smsValidateCodeDao.saveSmsValidateCode(smsValidateCodePhone);
        } else {
            // 修改验证码
            smsValidateCodePhone.setCode(RandomUtil.randomNumbers(6));
            smsValidateCodePhone.setValidTime(new Date());
            smsValidateCodeDao.updateSmsValidateCode(smsValidateCodePhone);
        }
        return smsValidateCodePhone.getCode();
    }

    @Override
    public ActionResult checkSmsValidateCode(String phone, String code) {
        ActionResult actionResult;
        SmsValidateCode smsValidateCode = smsValidateCodeDao
                .getSmsValidateCodeByPhone(phone);
        if (smsValidateCode != null) {
            // 输入的验证码和库里验证码是否一致
            if (smsValidateCode.getCode().equals(code)) {
                // 验证码是否失效
                int seconds = IceDateOperationUtils.betweenSecond(
                        smsValidateCode.getValidTime(), new Date());
                if (seconds <= SystemConstant.VERIFICATION_CODE) {
                    actionResult = actionResultService.callBackResult(true, 0,
                            "验证通过");
                } else {
                    actionResult = actionResultService.callBackResult(true, 0,
                            "验证码已过期");
                }
            } else {
                actionResult = actionResultService.callBackResult(true, 0,
                        "验证码错误");
            }
        } else {
            actionResult = actionResultService.callBackResult(true, 0,
                    "验证码不存在");
        }
        return actionResult;
    }
}
