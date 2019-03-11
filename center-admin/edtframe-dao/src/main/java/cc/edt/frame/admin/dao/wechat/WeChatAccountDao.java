package cc.edt.frame.admin.dao.wechat;

import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.wechat.WeChatAccount;

import java.util.List;

/**
 * 微信账号信息
 *
 * @author 奚艺轩
 * @date 2017/12/20 8:58
 */
public interface WeChatAccountDao {

    /**
     * 保存微信账号信息
     *
     * @param weChatAccount WeChatAccount
     * @author 奚艺轩
     * @date 2017-6-14 4:32:31
     */
    void saveWeChatAccount(WeChatAccount weChatAccount);

    /**
     * 修改微信账号信息
     *
     * @param weChatAccount weChatAccount
     * @author 奚艺轩
     * @date 2017-6-14 4:32:42
     */
    void updateWeChatAccount(WeChatAccount weChatAccount);

    /**
     * 删除微信账号信息
     *
     * @param id id
     * @author 奚艺轩
     * @date 2017-6-14 4:32:50
     */
    void deleteWeChatAccount(String id);

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
     * 根据查询条件获取微信账号信息
     *
     * @param findCondition findCondition
     * @return List<WeChatAccount>
     * @author 奚艺轩
     * @date 2017-6-14 4:37:16
     */
    List<WeChatAccount> listWeChatAccountByCondition(
            FindCondition findCondition);

    /**
     * 获取微信管理全部信息
     *
     * @return List<WeChatAccount>
     * @author 奚艺轩
     * @date 2017-6-14 4:37:25
     */
    List<WeChatAccount> listAllWeChatAccount();

    /**
     * 根据tokenFlag得到token
     *
     * @param tokenFlag tokenFlag
     * @return String
     * @author 奚艺轩
     * @date 2017-7-5 11:23:28
     */
    String getTokenByTokenFlag(String tokenFlag);

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
     * 根据登陆ID获取可以操作的微信账号
     *
     * @param loginId loginId
     * @return java.util.List<cc.edt.iceframe5.model.entity.base.WeChatAccount>
     * @author 刘钢
     * @date 2018/3/28 20:33
     */
    List<WeChatAccount> listWeChatAccountByloginId(String loginId);

    /**
     * 根据机构ID获取
     *
     * @param mechanismsId mechanismsId
     * @return cc.edt.iceframe5.base.entity.WeChatAccount
     * @author 刘钢
     * @date 2018/3/30 13:43
     */
    WeChatAccount getWeChatAccountByMemchanismsId(String mechanismsId);

    /**
     * 重复项检查
     *
     * @param weChatAccount weChatAccount
     * @return java.util.List<cc.edt.frame.core.wechat.entity.WeChatAccount>
     * @author 刘钢
     * @date 2018/8/28 21:17
     */
    List<WeChatAccount> checkSame(WeChatAccount weChatAccount);
}
