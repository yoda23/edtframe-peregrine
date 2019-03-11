package cc.edt.frame.security.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.security.bean.UserEncrypt;
import cc.edt.frame.security.service.WeChatOauthService;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信授权
 *
 * @author 刘钢
 * @date 2018/7/19 10:22
 */
@RestController
@CrossOrigin
@RequestMapping("/wechat/oauth")
@Slf4j
public class WeChatOauthController extends BaseController {
    @Resource
    private WeChatOauthService weChatOauthService;

    /**
     * 登录凭证校验
     *
     * @param code code
     * @author 刘钢
     * @date 2018/7/19 11:18
     */
    @PostMapping("/jsCode2Session/{code}/{appid}")
    public void jsCode2Session(@PathVariable("code") String code,
                               @PathVariable("appid") String appid) {
        writerToPageByJsonNoNull(
                weChatOauthService.jsCode2Session(code, appid));
    }

    /**
     * 检查sessionId
     *
     * @param sessionId sessionId
     * @author 刘钢
     * @date 2018/7/23 12:41
     */
    @PostMapping("/checkSession/{sessionId}")
    public void checkSession(@PathVariable("sessionId") String sessionId) {
        writerToPageByJsonNoNull(weChatOauthService.checkSession(sessionId));
    }

    /**
     * 小程序用户数据解密
     *
     * @param userEncrypt userEncrypt
     * @author 刘钢
     * @date 2018/7/19 13:47
     */
    @PostMapping("/userInfo")
    public void decryptUserInfo(@RequestBody UserEncrypt userEncrypt) {
        writerToPageByJsonNoNull(
                weChatOauthService.decryptUserInfo(userEncrypt));
    }

    /**
     * 获取公众号openid
     *
     * @author 刘艳柔
     * @date 2018/8/8 16:41
     */
    @PostMapping("/getWeChatOpenid")
    public String getWeChatOpenid(@RequestParam String code,
                                  @RequestParam String state) {
        weChatOauthService.getWeChatOpenid(code);
        return "authorize";
    }
}
