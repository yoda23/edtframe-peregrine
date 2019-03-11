package cc.edt.frame.wechat.service.component;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import cc.edt.frame.admin.dao.wechat.WeChatAccountDao;
import cc.edt.frame.admin.dao.wechat.WeChatFansDao;
import org.dom4j.DocumentException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.service.AccessTokenService;
import cc.edt.frame.model.entity.wechat.WeChatAccount;
import cc.edt.frame.model.entity.wechat.WeChatFans;
import cc.edt.iceutils3.date.IceDateFormatUtils;
import cc.edt.iceutils3.json.IceFastJsonUtils;
import cc.edt.iceutils3.wechat.even.IceWeChatEventParseFactory;
import cc.edt.iceutils3.wechat.even.bean.SubscribeEvent;
import cc.edt.iceutils3.wechat.even.parse.SubscribeEventParse;
import cc.edt.iceutils3.wechat.fans.info.IceWeChatFansInfoUtils;
import cc.edt.iceutils3.wechat.fans.info.bean.WeChatFansInfoResult;
import cc.edt.iceutils3.wechat.security.bean.AccessTokenResult;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信粉丝事件解析
 *
 * @author 刘钢
 * @date 2018/8/28 16:12
 */
@Component
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class WeChatFansEventReceive {
    @Resource
    private WeChatFansDao weChatFansDao;
    @Resource
    private AccessTokenService accessTokenService;
    @Resource
    private WeChatAccountDao weChatAccountDao;

    /**
     * 粉丝关注
     *
     * @param event event
     * @author 刘钢
     * @date 2018/9/17 11:22
     */
    public void subscribeEvent(String event) {
        SubscribeEvent subscribeEvent;
        try {
            subscribeEvent = IceWeChatEventParseFactory.evenPares(event,
                    SubscribeEvent.class, SubscribeEventParse.class);

            // 拉取粉丝信息
            ActionResult<AccessTokenResult, String> actionResult = accessTokenService
                    .getAccessTokenByOriginalId(subscribeEvent.getToUserName());
            WeChatAccount weChatAccount = weChatAccountDao
                    .getWeChatAccountByOriginalId(
                            subscribeEvent.getToUserName());
            if (actionResult.getStatus().isSuccess()) {
                AccessTokenResult accessTokenResult = actionResult.getResult();
                WeChatFansInfoResult weChatFansInfoResult = IceWeChatFansInfoUtils
                        .getUserInfo(accessTokenResult.getAccessToken(),
                                subscribeEvent.getFromUserName());
                WeChatFans weChatFans = new WeChatFans();
                BeanUtils.copyProperties(
                        weChatFansInfoResult.getListWeChatFansInfoBean().get(0),
                        weChatFans);
                weChatFans.setSubscribeTime(
                        IceDateFormatUtils.getTimeStampToDate(Long.valueOf(
                                weChatFansInfoResult.getListWeChatFansInfoBean()
                                        .get(0).getSubscribeTime())));
                WeChatFans weChatFansInfoOpenId = weChatFansDao
                        .getWeChatFansByOpenId(
                                subscribeEvent.getFromUserName());
                if (weChatFansInfoOpenId == null) {
                    if (weChatAccount != null) {
                        weChatFans.setAccountId(weChatAccount.getId());
                    }
                    weChatFans.setId(IdUtil.simpleUUID());
                    weChatFansDao.saveWeChatFans(weChatFans);
                } else {
                    weChatFansDao.updateWeChatFansByOpenId(weChatFans);
                }
            } else {
                log.info(IceFastJsonUtils.toJsonString(actionResult));
            }
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | IOException | DocumentException
                | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    /**
     * 粉丝取消关注
     *
     * @param event event
     * @author 刘钢
     * @date 2018/9/17 11:23
     */
    public void unSubscribeEvent(String event) {
        try {
            SubscribeEvent subscribeEvent = IceWeChatEventParseFactory
                    .evenPares(event, SubscribeEvent.class,
                            SubscribeEventParse.class);
            WeChatFans weChatFans = new WeChatFans();
            weChatFans.setSubscribe("2");
            weChatFans.setUnsubscribeTime(IceDateFormatUtils.getTimeStampToDate(
                    Long.valueOf(subscribeEvent.getCreateTime())));
            weChatFans.setOpenId(subscribeEvent.getFromUserName());
            weChatFansDao.updateWeChatFansUnsubscribeTimeByOpenId(weChatFans);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
