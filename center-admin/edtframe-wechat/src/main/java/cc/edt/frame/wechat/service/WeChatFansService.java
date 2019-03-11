package cc.edt.frame.wechat.service;

import java.util.List;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.wechat.WeChatFans;

/**
 * 微信粉丝的接口
 *
 * @author 奚艺轩
 * @date 2017/12/20 9:07
 */
public interface WeChatFansService {

    /**
     * 根据条件查询微信粉丝
     *
     * @param condition condition
     * @return List<WeChatFans>
     * @author 奚艺轩
     * @date 2017-6-22上午11:54:57
     */
    List<WeChatFans> listWeChatFansByCondition(FindCondition condition);

    /**
     * 拉取微信粉丝信息
     *
     * @param accountId accountId
     * @return cc.edt.frame.core.common.result.ActionResult
     * @author 奚艺轩
     * @date 2018/9/12 14:39
     */
    ActionResult syncWeChatFans(String accountId);

}
