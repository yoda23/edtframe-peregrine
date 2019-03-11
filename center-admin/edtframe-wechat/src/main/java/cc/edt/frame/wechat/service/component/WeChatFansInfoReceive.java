package cc.edt.frame.wechat.service.component;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import cc.edt.frame.common.constant.SystemConstant;
import cc.edt.frame.admin.dao.wechat.WeChatFansDao;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cc.edt.frame.common.exception.CustomerException;
import cc.edt.frame.common.exception.CustomerExceptionCode;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.service.AccessTokenService;
import cc.edt.frame.model.entity.wechat.WeChatFans;
import cc.edt.frame.wechat.service.component.fans.WeChatFansInfoList;
import cc.edt.iceutils3.date.IceDateFormatUtils;
import cc.edt.iceutils3.wechat.fans.info.IceWeChatFansInfoUtils;
import cc.edt.iceutils3.wechat.fans.info.bean.WeChatFansInfoBean;
import cc.edt.iceutils3.wechat.fans.info.bean.WeChatFansInfoResult;
import cc.edt.iceutils3.wechat.security.bean.AccessTokenResult;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 奚艺轩
 * @date 2018/9/12 14:20
 */
@Component
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class WeChatFansInfoReceive {
    @Resource
    private AccessTokenService accessTokenService;
    @Resource
    private WeChatFansDao weChatFansDao;
    /**
     * 微信批量一次最大可拉取的粉丝数量
     */
    private final static Integer ONCE_MAXNUM_TO_FANS = 100;

    /**
     * 同步粉丝信息
     *
     * @param weChatFansInfoList weChatFansInfoList
     * @author 刘钢
     * @date 2018/12/12 15:57
     */
    @RabbitHandler
    @RabbitListener(queues = "wechat.fans")
    public void syncFansInfo(WeChatFansInfoList weChatFansInfoList) {
        String accountId = weChatFansInfoList.getAccountId();
        List<String> listOpenIds = weChatFansInfoList.getListOpenIds();
        ActionResult<AccessTokenResult, String> actionResult = accessTokenService
                .getAccessTokenByAccountId(accountId);
        String accessToken = "";
        if (actionResult.getStatus().isSuccess()) {
            AccessTokenResult accessTokenResult = actionResult.getResult();
            accessToken = accessTokenResult.getAccessToken();
        }
        List<String> listOpenIdTemp = Lists.newArrayList();
        // 获取拉取微信粉丝信息的次数

        int getFansCount;
        if (listOpenIds.size() % ONCE_MAXNUM_TO_FANS == 0) {
            getFansCount = listOpenIds.size() / ONCE_MAXNUM_TO_FANS;
        } else {
            getFansCount = listOpenIds.size() / ONCE_MAXNUM_TO_FANS + 1;
        }
        // 从集合的第几个下标开始拉取
        int startIndex = 0;
        // 剩余数量
        int surplus = listOpenIds.size();
        // 单次拉取数量
        int singleFansCount = 100;
        for (int i = 0; i < getFansCount; i++) {
            if (surplus < 100) {
                singleFansCount = surplus;
            }
            for (int j = 0; j < singleFansCount; j++, startIndex++) {
                listOpenIdTemp.add(listOpenIds.get(startIndex));
            }
            // 批量获取微信粉丝信息
            WeChatFansInfoResult weChatFansInfoResult = null;
            try {
                weChatFansInfoResult = IceWeChatFansInfoUtils
                        .getUserInfoByBatch(accessToken, listOpenIdTemp);
            } catch (IOException | NoSuchAlgorithmException
                    | KeyManagementException e) {
                e.printStackTrace();
                throw new CustomerException(
                        CustomerExceptionCode.NETWORK_EXCEPTION_CODE,
                        CustomerExceptionCode.NETWORK_EXCEPTION_MESSAGE);
            }
            if (SystemConstant.SUCCESS_STRING_0
                    .equals(weChatFansInfoResult.getErrorInfo().getErrCode())) {
                List<WeChatFansInfoBean> listWeChatWeChatFansInfoBean = weChatFansInfoResult
                        .getListWeChatFansInfoBean();
                saveOrUpdateWxFansInfo(accountId, listWeChatWeChatFansInfoBean);

            } else {
                log.info("批量获取粉丝信息失败->"
                        + weChatFansInfoResult.getErrorInfo().getErrCode() + ":"
                        + weChatFansInfoResult.getErrorInfo().getErrMsg());
            }
            surplus -= singleFansCount;
            listOpenIdTemp.clear();
        }
    }

    /**
     * 更新粉丝信息
     *
     * @param accountId      accountId
     * @param listWxFansInfo listWxFansInfo
     * @author 刘钢
     * @date 2018/12/12 15:57
     */
    public void saveOrUpdateWxFansInfo(String accountId,
                                       List<WeChatFansInfoBean> listWxFansInfo) {
        for (WeChatFansInfoBean wxFansInfoBean : listWxFansInfo) {
            // 根据openid查询微信粉丝
            WeChatFans weChatFansOpenId = weChatFansDao
                    .getWeChatFansByOpenId(wxFansInfoBean.getOpenId());
            WeChatFans weChatFans = new WeChatFans();
            // 判断微信粉丝存不存在
            if (weChatFansOpenId == null) {
                // 保存微信粉丝
                BeanCopier copier = BeanCopier.create(WeChatFansInfoBean.class,
                        WeChatFans.class, false);
                copier.copy(wxFansInfoBean, weChatFans, null);
                // 关注时间
                if (wxFansInfoBean.getSubscribeTime() != null) {
                    weChatFans.setSubscribeTime(
                            IceDateFormatUtils.getTimeStampToDate(new Long(
                                    wxFansInfoBean.getSubscribeTime())));
                }
                weChatFans.setId(IdUtil.simpleUUID());
                weChatFans.setAccountId(accountId);
                weChatFansDao.saveWeChatFans(weChatFans);
            } else {
                // 修改微信粉丝
                BeanCopier copier = BeanCopier.create(WeChatFansInfoBean.class,
                        WeChatFans.class, false);
                copier.copy(wxFansInfoBean, weChatFans, null);
                if (wxFansInfoBean.getSubscribeTime() != null) {
                    weChatFans.setSubscribeTime(
                            IceDateFormatUtils.getTimeStampToDate(Long.valueOf(
                                    wxFansInfoBean.getSubscribeTime())));
                }
                weChatFansDao.updateWeChatFansByOpenId(weChatFans);
            }
        }
    }
}
