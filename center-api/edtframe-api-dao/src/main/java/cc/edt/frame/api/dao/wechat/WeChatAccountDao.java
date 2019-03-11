package cc.edt.frame.api.dao.wechat;

import cc.edt.frame.model.entity.wechat.WeChatAccount;

/**
 * 微信账号信息
 *
 * @author 奚艺轩
 * @date 2017/12/20 8:58
 */
public interface WeChatAccountDao {

    /**
     * 根据原始id获取微信账号信息
     *
     * @param originalId originalId
     * @return WeChatAccount
     * @author 奚艺轩
     * @date 2017-6-14 4:33:07
     */
    WeChatAccount getWeChatAccountByOriginalId(String originalId);

    /**
     * 根据id获取微信账号信息
     *
     * @param id id
     * @return WeChatAccount
     * @author 奚艺轩
     * @date 2017-6-14 4:33:44
     */
    WeChatAccount getWeChatAccountById(String id);

    /**
     * 根据appId获取微信信息
     *
     * @param appId appId
     * @return cc.edt.iceframe5.model.entity.base.WeChatAccount
     * @author 刘钢
     * @date 2018/3/12 10:24
     */
    WeChatAccount getWeChatAccountByAppId(String appId);

    /**
     * 根据tokenFlag得到token
     *
     * @param tokenFlag tokenFlag
     * @return String
     * @author 奚艺轩
     * @date 2017-7-5 11:23:28
     */
    String getTokenByTokenFlag(String tokenFlag);
}
