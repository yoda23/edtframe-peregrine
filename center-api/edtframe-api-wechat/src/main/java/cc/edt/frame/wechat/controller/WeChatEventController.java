package cc.edt.frame.wechat.controller;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import cc.edt.frame.common.constant.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.wechat.service.WeChatEventService;
import cc.edt.iceutils3.wechat.security.IceWeChatCallBackTokenUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信回调
 *
 * @author 刘钢
 * @date 2018/9/17 9:44
 */
@RestController
@CrossOrigin
@RequestMapping("/wechat/callback")
@Slf4j
public class WeChatEventController extends BaseController {
    @Resource
    private WeChatEventService weChatEventService;

    /***
     * 微信公众号回调地址token验证
     *
     * @param flag
     *            flag
     * @author 刘艳柔
     * @date 2017/12/20 14:50
     */
    @GetMapping(value = "/{flag}")
    public void token(@PathVariable(value = "flag") String flag,
                      @RequestParam("signature") String signature,
                      @RequestParam("echostr") String echostr,
                      @RequestParam("timestamp") String timestamp,
                      @RequestParam("nonce") String nonce)
            throws NoSuchAlgorithmException {
        String token = weChatEventService.getTokenByTokenFlag(flag);
        String signatureToken = IceWeChatCallBackTokenUtils
                .getWeChatTokenSign(token, timestamp, nonce);
        if (signature.equals(signatureToken)) {
            writerToPageByString(echostr);
            return;
        }
        writerToPageByString("");

    }

    /**
     * 微信回调信息处理
     *
     * @param flag     flag
     * @param eventXml eventXml
     * @author 刘艳柔
     * @date 2017/12/20 14:50
     */
    @PostMapping(value = "/{flag}")
    public void callBack(@PathVariable(value = "flag") String flag,
                         @RequestBody String eventXml) {
        ActionResult result = weChatEventService.sendWeChatEventQueue(eventXml);
        if (result.getStatus().isSuccess()) {
            writerToPageByString(SystemConstant.SUCCESS_STRING);
        }
    }
}
