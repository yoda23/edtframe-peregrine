package cc.edt.frame.admin.dao.wechat;

import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.wechat.WeChatFans;

import java.util.List;

/**
 * 微信粉丝
 *
 * @author 奚艺轩
 * @date 2017/12/20 8:59
 */
public interface WeChatFansDao {
    /**
     * 保存微信粉丝
     *
     * @param weChatFans weChatFans
     * @author 奚艺轩
     * @date 2017-6-16下午3:06:11
     */
    void saveWeChatFans(WeChatFans weChatFans);

    /**
     * 修改微信粉丝
     *
     * @param weChatFans weChatFans
     * @author 奚艺轩
     * @date 2017-6-16下午3:06:26
     */
    void updateWeChatFansByOpenId(WeChatFans weChatFans);

    /**
     * 根据openid查询微信粉丝
     *
     * @param openId openId
     * @return WeChatFans
     * @author 奚艺轩
     * @date 2017-6-16下午3:09:01
     */
    WeChatFans getWeChatFansByOpenId(String openId);

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
     * 修改粉丝的取消关注时间
     *
     * @param weChatFans weChatFans
     * @author 刘艳柔
     * @date 2017/12/15 16:22
     */
    void updateWeChatFansUnsubscribeTimeByOpenId(WeChatFans weChatFans);

    /**
     * 根据公众号ID获取粉丝数据
     *
     * @param accountId accountId
     * @return java.util.List<cc.edt.iceframe5.wechat.entity.WeChatFans>
     * @author 刘钢
     * @date 2018/4/13 16:59
     */
    List<WeChatFans> listWeChatFansByAccountId(String accountId);

}
