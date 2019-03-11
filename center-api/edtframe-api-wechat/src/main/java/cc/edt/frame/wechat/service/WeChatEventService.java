package cc.edt.frame.wechat.service;

import cc.edt.frame.common.result.ActionResult;

/**
 * 微信事件处理
 *
 * @author 刘钢
 * @date 2018/9/6 13:06
 */
public interface WeChatEventService {

    /**
     * 发送微信事件处理队列
     *
     * @param event event
     * @return cc.edt.frame.core.wechat.result.ActionResult
     * @author 刘钢
     * @date 2018/9/6 13:09
     */
    ActionResult sendWeChatEventQueue(String event);

    /**
     * 获取微信回调token标识
     *
     * @param flag flag
     * @return cc.edt.frame.model.entity.wechat.WeChatAccount
     * @author 刘钢
     * @date 2018/11/2 9:39
     */
    String getTokenByTokenFlag(String flag);
}
