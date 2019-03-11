package cc.edt.frame.security.service;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.DatasResult;
import cc.edt.frame.security.bean.UserEncrypt;
import cc.edt.iceutils3.wechat.security.bean.JsCode2SessionResult;
import cc.edt.iceutils3.wechat.security.bean.MiNiProgramUserInfo;

/**
 * 微信授权
 *
 * @author 刘钢
 * @date 2018/10/31 9:01
 */
public interface WeChatOauthService {
    /**
     * jsCode2Session
     *
     * @param code  code
     * @param appId appId
     * @return cc.edt.frame.common.result.ActionResult
     * @author 刘钢
     * @date 2018/7/23 11:14
     */
    ActionResult<DatasResult<JsCode2SessionResult>, String> jsCode2Session(
            String code, String appId);

    /**
     * 检查sessionId
     *
     * @param sessionId sessionId
     * @return cc.edt.frame.common.result.ActionResult
     * @author 刘钢
     * @date 2018/7/23 11:46
     */
    ActionResult checkSession(String sessionId);

    /**
     * 解密用户信息
     *
     * @param userEncrypt userEncrypt
     * @return cc.edt.frame.common.result.ActionResult
     * @author 刘钢
     * @date 2018/7/23 12:44
     */
    ActionResult<DatasResult<MiNiProgramUserInfo>, String> decryptUserInfo(
            UserEncrypt userEncrypt);

    /**
     * 根据oauth2.0的code获取openid
     *
     * @param code code
     * @author 刘钢
     * @date 2018/10/31 11:21
     */
    void getWeChatOpenid(String code);
}
