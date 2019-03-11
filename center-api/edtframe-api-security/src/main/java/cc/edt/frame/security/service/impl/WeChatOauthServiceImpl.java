package cc.edt.frame.security.service.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import cc.edt.frame.common.result.DatasResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cc.edt.frame.common.constant.SystemConstant;
import cc.edt.frame.common.constant.WeChatDictionary;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.api.dao.wechat.WeChatAccountDao;
import cc.edt.frame.model.entity.wechat.WeChatAccount;
import cc.edt.frame.security.bean.UserEncrypt;
import cc.edt.frame.security.service.WeChatOauthService;
import cc.edt.iceutils3.date.IceDateFormatUtils;
import cc.edt.iceutils3.date.IceDateOperationUtils;
import cc.edt.iceutils3.wechat.fans.info.IceWeChatFansInfoUtils;
import cc.edt.iceutils3.wechat.fans.info.bean.OauthCodeInfo;
import cc.edt.iceutils3.wechat.security.IceWeChatAccessTokenUtils;
import cc.edt.iceutils3.wechat.security.IceWeChatMiniProgramDecodeUtils;
import cc.edt.iceutils3.wechat.security.bean.JsCode2SessionResult;
import cc.edt.iceutils3.wechat.security.bean.MiNiProgramUserInfo;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信授权
 *
 * @author 刘钢
 * @date 2018/10/31 9:15
 */
@Service
@Slf4j
public class WeChatOauthServiceImpl implements WeChatOauthService {
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ActionResultService actionResultService;
    @Resource
    private WeChatAccountDao weChatAccountDao;

    @Override
    public ActionResult<DatasResult<JsCode2SessionResult>, String> jsCode2Session(
            String code, String appId) {
        ActionResult<DatasResult<JsCode2SessionResult>, String> actionResult = new ActionResult<>();
        try {
            JsCode2SessionResult jsCode2SessionResult = IceWeChatAccessTokenUtils
                    .createJsCode2Session(appId,
                            WeChatDictionary.WECHAT_DICTIONARY_MAP.get(appId),
                            code);
            DatasResult<JsCode2SessionResult> datasResult = new DatasResult<>();
            datasResult.setDatas(jsCode2SessionResult);
            if (SystemConstant.SUCCESS_STRING_0
                    .equals(jsCode2SessionResult.getErrorInfo().getErrCode())) {
                String time = IceDateFormatUtils
                        .getNowDateTime("yyyyMMddHHmmss");
                String ranodm = RandomUtil.randomNumbers(10);
                String key = time + ranodm + code
                        + jsCode2SessionResult.getOpenId()
                        + jsCode2SessionResult.getSessionKey();
                String session = DigestUtils.md5Hex(key);
                jsCode2SessionResult.setAddTime(new Date());
                redisTemplate.opsForValue().set(session, jsCode2SessionResult,
                        5400, TimeUnit.SECONDS);
                jsCode2SessionResult.setSessionKey(session);
                actionResult = actionResultService.callBackResult(true, 0,
                        "请求成功", datasResult, null);
            } else {
                actionResult = actionResultService.callBackResult(false, -1,
                        jsCode2SessionResult.getErrorInfo().getErrMsg(),
                        datasResult, null);
            }
        } catch (IOException | NoSuchAlgorithmException
                | KeyManagementException e) {
            e.printStackTrace();
        }

        return actionResult;
    }

    @Override
    public ActionResult checkSession(String sessionId) {
        ActionResult<DatasResult<JsCode2SessionResult>, String> baseResult = actionResultService
                .callBackResult(false, -1, "sessionId无效");
        JsCode2SessionResult jsCode2SessionResult = (JsCode2SessionResult) redisTemplate
                .opsForValue().get(sessionId);
        if (jsCode2SessionResult != null) {
            if (IceDateOperationUtils.betweenSecond(
                    jsCode2SessionResult.getAddTime(), new Date()) >= 6000) {
                baseResult = actionResultService.callBackResult(false, -1,
                        "sessionId无效");
            } else {
                baseResult = actionResultService.callBackResult();
            }
        }
        return baseResult;
    }

    @Override
    public ActionResult<DatasResult<MiNiProgramUserInfo>, String> decryptUserInfo(
            UserEncrypt userEncrypt) {
        ActionResult<DatasResult<MiNiProgramUserInfo>, String> actionResult = actionResultService
                .callBackResult(false, -1, "sessionId无效");
        JsCode2SessionResult jsCode2SessionResult = (JsCode2SessionResult) redisTemplate
                .opsForValue().get(userEncrypt.getSessionKey());
        if (jsCode2SessionResult != null) {
            try {
                MiNiProgramUserInfo miNiProgramUserInfo = IceWeChatMiniProgramDecodeUtils
                        .decrypt(userEncrypt.getEncryptedData(),
                                jsCode2SessionResult.getSessionKey(),
                                userEncrypt.getIv(), "UTF-8");
                DatasResult<MiNiProgramUserInfo> datasResult = new DatasResult<>();
                datasResult.setDatas(miNiProgramUserInfo);
                if (SystemConstant.SUCCESS_STRING_0.equals(
                        miNiProgramUserInfo.getErrorInfo().getErrCode())) {
                    actionResult = actionResultService.callBackResult(true, 0,
                            "请求成功", datasResult, null);
                } else {
                    actionResult = actionResultService.callBackResult(false, -1,
                            miNiProgramUserInfo.getErrorInfo().getErrMsg());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                actionResult = actionResultService.callBackResult(false, -1,
                        "获取用户数据异常");
            }

        }
        return actionResult;
    }

    @Override
    public void getWeChatOpenid(String code) {
        WeChatAccount weChatAccount = weChatAccountDao
                .getWeChatAccountByAppId(WeChatDictionary.WECHAT_APPID);
        if (weChatAccount != null) {
            try {
                OauthCodeInfo oAuthCodeInfo = IceWeChatFansInfoUtils
                        .getOpenIdByCode(WeChatDictionary.WECHAT_APPID,
                                weChatAccount.getAppSecret(), code);
                if (SystemConstant.SUCCESS_STRING_0
                        .equals(oAuthCodeInfo.getErrorInfo().getErrCode())) {
                    log.info("公众号openid为:" + oAuthCodeInfo.getOpenid());
                } else {
                    log.info("获取openid错误信息:"
                            + oAuthCodeInfo.getErrorInfo().getErrMsg());

                }
            } catch (IOException | NoSuchAlgorithmException
                    | KeyManagementException e) {
                e.printStackTrace();
            }
        }
    }
}
