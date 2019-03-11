package cc.edt.frame.sms.controller;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.sms.service.SmsService;
import cc.edt.frame.sms.vo.SmsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短信
 *
 * @author 刘钢
 * @date 2018/12/19 11:55
 */
@RestController
@CrossOrigin
@RequestMapping("/sms")
@Slf4j
public class SmsController extends BaseController {

    @Resource(name = "JiSuSmsService")
    private SmsService jiSuSmsService;

    /**
     * 急速短信
     *
     * @param smsVO smsVO
     * @author 刘钢
     * @date 2018/12/19 13:22
     */
    @RequestMapping("/jisu")
    public void sendSms(@RequestBody SmsVO smsVO) {
        writerToPageByJsonNoNull(
                jiSuSmsService.sendSms(smsVO.getPhone(), smsVO.getContent()));
    }
}
