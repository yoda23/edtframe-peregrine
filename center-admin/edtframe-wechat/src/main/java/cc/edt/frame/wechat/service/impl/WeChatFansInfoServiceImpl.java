package cc.edt.frame.wechat.service.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import cc.edt.frame.common.constant.SystemConstant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cc.edt.frame.common.exception.CustomerException;
import cc.edt.frame.common.exception.CustomerExceptionCode;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.common.service.AccessTokenService;
import cc.edt.frame.admin.dao.wechat.WeChatFansDao;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.wechat.WeChatFans;
import cc.edt.frame.wechat.service.WeChatFansService;
import cc.edt.frame.wechat.service.component.fans.WeChatFansInfoList;
import cc.edt.iceutils3.json.IceFastJsonUtils;
import cc.edt.iceutils3.wechat.fans.info.IceWeChatFansInfoUtils;
import cc.edt.iceutils3.wechat.fans.info.bean.WeChatOpenIdListResult;
import cc.edt.iceutils3.wechat.security.bean.AccessTokenResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信粉丝
 *
 * @author 刘钢
 * @date 2018/3/10 12:50
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class WeChatFansInfoServiceImpl implements WeChatFansService {
    @Resource
    private WeChatFansDao weChatFansDao;
    @Resource
    private AccessTokenService accessTokenService;
    @Resource
    private ActionResultService actionResultService;
    @Resource
    private AmqpTemplate rabbitTemplate;

    @Override
    public List<WeChatFans> listWeChatFansByCondition(FindCondition condition) {
        PageHelper.startPage(condition.getPage(), condition.getLimit());
        List<WeChatFans> listFansInfo = weChatFansDao
                .listWeChatFansByCondition(condition);
        PageInfo<WeChatFans> pageInfo = new PageInfo<>(listFansInfo);
        condition.setTotal(pageInfo.getTotal());
        return listFansInfo;
    }

    @Override
    public ActionResult syncWeChatFans(String accountId) {
        ActionResult<AccessTokenResult, String> actionResult = accessTokenService
                .getAccessTokenByAccountId(accountId);
        if (actionResult.getStatus().isSuccess()) {
            // 第一次拉取
            AccessTokenResult accessTokenResult = actionResult.getResult();
            WeChatOpenIdListResult weChatOpenIdListResult;
            try {
                weChatOpenIdListResult = IceWeChatFansInfoUtils
                        .getWeChatFansOpenIdList(
                                accessTokenResult.getAccessToken(), "");
            } catch (IOException | NoSuchAlgorithmException
                    | KeyManagementException e) {
                e.printStackTrace();
                throw new CustomerException(
                        CustomerExceptionCode.NETWORK_EXCEPTION_CODE,
                        CustomerExceptionCode.NETWORK_EXCEPTION_MESSAGE);
            }
            log.info("第一次拉取openid-->"
                    + IceFastJsonUtils.toJsonString(weChatOpenIdListResult));
            if (SystemConstant.SUCCESS_STRING_0.equals(
                    weChatOpenIdListResult.getErrorInfo().getErrCode())) {
                // openId列表总数
                int openIdTotal = weChatOpenIdListResult
                        .getWeChatOpenIdListBean().getTotal();
                // openId当前拉回的数量
                int openIdCount = weChatOpenIdListResult
                        .getWeChatOpenIdListBean().getCount();
                // 剩余待处理的openId数量
                int openIdSurplus = openIdTotal;
                // 下一组从哪个openId开始
                String nextOpenId = weChatOpenIdListResult
                        .getWeChatOpenIdListBean().getNextOpenId();
                // 如果还有剩余数量，继续循环拉取
                while (openIdSurplus > 0 && weChatOpenIdListResult
                        .getWeChatOpenIdListBean().getCount() != 0) {
                    WeChatFansInfoList weChatFansInfoList = new WeChatFansInfoList();
                    weChatFansInfoList.setAccountId(accountId);
                    weChatFansInfoList.setListOpenIds(weChatOpenIdListResult
                            .getWeChatOpenIdListBean().getData().getOpenid());
                    // 创建队列
                    rabbitTemplate.convertAndSend("wechat.fans",
                            weChatFansInfoList);
                    openIdSurplus -= openIdCount;
                    if (openIdSurplus > 0) {
                        try {
                            weChatOpenIdListResult = IceWeChatFansInfoUtils
                                    .getWeChatFansOpenIdList(
                                            accessTokenResult.getAccessToken(),
                                            nextOpenId);
                        } catch (IOException | NoSuchAlgorithmException
                                | KeyManagementException e) {
                            e.printStackTrace();
                            throw new CustomerException(
                                    CustomerExceptionCode.NETWORK_EXCEPTION_CODE,
                                    CustomerExceptionCode.NETWORK_EXCEPTION_MESSAGE);
                        }
                        if (SystemConstant.SUCCESS_STRING_0
                                .equals(weChatOpenIdListResult.getErrorInfo()
                                        .getErrCode())) {
                            openIdCount = weChatOpenIdListResult
                                    .getWeChatOpenIdListBean().getCount();
                            nextOpenId = weChatOpenIdListResult
                                    .getWeChatOpenIdListBean().getNextOpenId();
                        } else {
                            log.info("拉取粉丝列表错误->"
                                    + weChatOpenIdListResult.getErrorInfo()
                                    .getErrCode()
                                    + ":" + weChatOpenIdListResult
                                    .getErrorInfo().getErrMsg());
                        }
                    }

                }
            } else {
                log.info("拉取粉丝列表错误->"
                        + weChatOpenIdListResult.getErrorInfo().getErrCode()
                        + ":"
                        + weChatOpenIdListResult.getErrorInfo().getErrMsg());
            }
        } else {
            // 获取accessToken失败
            log.info("AccessTokenResult-->"
                    + actionResult.getStatus().getMessage());
        }

        return actionResultService.callBackResult(true, 0,
                "粉丝拉取任务启动成功，请稍后查询数据");
    }
}
