package cc.edt.frame.sms.service.impl;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.sms.result.JiSuSmsResult;
import cc.edt.frame.sms.service.SmsService;
import cc.edt.iceutils3.net.IceOkHttpUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * 急速短信网关
 *
 * @author 刘钢
 * @date 2018/12/19 11:39
 */
@Service("JiSuSmsService")
public class JiSuSmsServiceImpl implements SmsService {
    @Resource
    private ActionResultService actionResultService;

    @Override
    public ActionResult sendSms(String phone, String content) {
        ActionResult actionResult;
        String url = "http://m.edt.cc/sms/smsSend/smsSendMessage?entJsid=ljwk&templateCode=52f9f742"
                + "8a2843ef818e8b4a34dadee0&phone=" + phone + "&content="
                + content;
        try {
            String result = IceOkHttpUtils.doHttpGet(url);
            JiSuSmsResult jiSuSmsResult = JSON.parseObject(result,
                    JiSuSmsResult.class);
            if (jiSuSmsResult.getSuccess()) {
                actionResult = actionResultService.callBackResult(true, 0,
                        "发送成功");
            } else {
                actionResult = actionResultService.callBackResult(false, -1,
                        "发送失败");
            }
        } catch (IOException | KeyManagementException
                | NoSuchAlgorithmException e) {
            e.printStackTrace();
            actionResult = actionResultService.callBackResult(false, -1,
                    "发送失败");
        }
        return actionResult;
    }
}
