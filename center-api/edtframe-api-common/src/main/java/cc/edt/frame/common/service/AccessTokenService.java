package cc.edt.frame.common.service;

import cc.edt.frame.api.dao.wechat.WeChatAccountDao;
import cc.edt.frame.common.constant.SystemConstant;
import cc.edt.frame.common.exception.CustomerException;
import cc.edt.frame.common.exception.CustomerExceptionCode;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.model.entity.wechat.WeChatAccount;
import cc.edt.iceutils3.wechat.security.IceWeChatAccessTokenUtils;
import cc.edt.iceutils3.wechat.security.bean.AccessTokenResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 微信accessToken维护
 *
 * @author 刘钢
 * @date 2018/3/8 9:13
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class AccessTokenService {
    @Resource
    private WeChatAccountDao weChatAccountDao;
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ActionResultService actionResultService;

    /**
     * 获取微信accessToken
     *
     * @param appId appId
     * @return cc.edt.frame.common.result.ActionResult
     * @author 刘钢
     * @date 2018/12/12 15:59
     */
    public ActionResult<AccessTokenResult, String> getAccessTokenByAppId(
            String appId) {
        WeChatAccount weChatAccount = weChatAccountDao
                .getWeChatAccountByAppId(appId);
        // 没有找到对应的ID信息
        if (weChatAccount == null) {
            return actionResultService.callBackResult(false, -1, "没有找到微信账号");
        }
        return getAccessToken(weChatAccount);
    }

    /**
     * 获取微信accessToken
     *
     * @param accountId accountId
     * @return cc.edt.frame.common.result.ActionResult
     * @author 刘钢
     * @date 2018/12/12 15:59
     */
    public ActionResult<AccessTokenResult, String> getAccessTokenByAccountId(
            String accountId) {
        WeChatAccount weChatAccount = weChatAccountDao
                .getWeChatAccountById(accountId);
        // 没有找到对应的ID信息
        if (weChatAccount == null) {
            return actionResultService.callBackResult(false, -1, "没有找到微信账号");
        }
        return getAccessToken(weChatAccount);
    }

    /**
     * 获取微信accessToken
     *
     * @param originalId originalId
     * @return cc.edt.frame.common.result.ActionResult
     * @author 刘钢
     * @date 2018/12/12 15:59
     */
    public ActionResult<AccessTokenResult, String> getAccessTokenByOriginalId(
            String originalId) {
        WeChatAccount weChatAccount = weChatAccountDao
                .getWeChatAccountByOriginalId(originalId);
        // 没有找到对应的ID信息
        if (weChatAccount == null) {
            return actionResultService.callBackResult(false, -1, "没有找到微信账号");
        }
        return getAccessToken(weChatAccount);
    }

    /**
     * 获取accessToken
     *
     * @param weChatAccount weChatAccount
     * @return cc.edt.frame.core.common.result.ActionResult
     * @author 刘钢
     * @date 2018/9/11 8:48
     */
    private ActionResult<AccessTokenResult, String> getAccessToken(
            WeChatAccount weChatAccount) {
        AccessTokenResult accessTokenResult = (AccessTokenResult) redisTemplate
                .opsForValue().get(weChatAccount.getAppId() + "_accessToken");
        if (accessTokenResult == null) {
            // 从微信获取accesstoken
            try {
                accessTokenResult = IceWeChatAccessTokenUtils.createAccessToken(
                        weChatAccount.getAppId(), weChatAccount.getAppSecret());
            } catch (IOException | NoSuchAlgorithmException
                    | KeyManagementException e) {
                e.printStackTrace();
                throw new CustomerException(
                        CustomerExceptionCode.NETWORK_EXCEPTION_CODE,
                        CustomerExceptionCode.NETWORK_EXCEPTION_MESSAGE);
            }
            if (SystemConstant.SUCCESS_STRING_0
                    .equals(accessTokenResult.getErrorInfo().getErrCode())) {
                accessTokenResult.setAddTime(new Date());
                // redis缓存accessToken值5400秒
                redisTemplate.opsForValue().set(
                        weChatAccount.getAppId() + "_accessToken",
                        accessTokenResult, 5400, TimeUnit.SECONDS);
                return actionResultService.callBackResult(true, 0, "请求成功",
                        accessTokenResult, null);
            } else {
                return actionResultService.callBackResult(false, -1,
                        accessTokenResult.getErrorInfo().getErrMsg());
            }
        } else {
            return actionResultService.callBackResult(true, 0, "请求成功",
                    accessTokenResult, null);
        }
    }
}
