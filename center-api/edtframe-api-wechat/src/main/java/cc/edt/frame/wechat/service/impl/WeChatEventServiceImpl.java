package cc.edt.frame.wechat.service.impl;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.api.dao.wechat.WeChatAccountDao;
import cc.edt.frame.wechat.service.WeChatEventService;

/**
 * 微信事件处理
 *
 * @author 刘钢
 * @date 2018/9/6 13:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WeChatEventServiceImpl implements WeChatEventService {
    @Resource
    private AmqpTemplate rabbitTemplate;
    @Resource
    private ActionResultService actionResultService;
    @Resource
    private WeChatAccountDao weChatAccountDao;

    @Override
    public ActionResult sendWeChatEventQueue(String event) {
        rabbitTemplate.convertAndSend("wechat.event", event);
        return actionResultService.callBackResult();
    }

    @Override
    public String getTokenByTokenFlag(String flag) {
        return weChatAccountDao.getTokenByTokenFlag(flag);
    }
}
