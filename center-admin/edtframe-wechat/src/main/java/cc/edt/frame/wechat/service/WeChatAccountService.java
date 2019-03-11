package cc.edt.frame.wechat.service;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.wechat.WeChatAccount;

import java.util.List;

/**
 * 微信账号信息
 *
 * @author 刘钢
 * @date 2018/8/28 21:13
 */
public interface WeChatAccountService {
    /**
     * 保存微信账号信息
     *
     * @param weChatAccount WeChatAccount
     * @return cc.edt.frame.core.wechat.result.ActionResult
     * @author 奚艺轩
     * @date 2017-6-14 4:32:31
     */
    ActionResult saveWeChatAccount(WeChatAccount weChatAccount);

    /**
     * 修改微信账号信息
     *
     * @param weChatAccount weChatAccount
     * @return cc.edt.frame.core.wechat.result.ActionResult
     * @author 奚艺轩
     * @date 2017-6-14 4:32:42
     */
    ActionResult updateWeChatAccount(WeChatAccount weChatAccount);

    /**
     * 删除微信账号信息
     *
     * @param id id
     * @return cc.edt.frame.core.wechat.result.ActionResult
     * @author 奚艺轩
     * @date 2017-6-14 4:32:50
     */
    ActionResult deleteWeChatAccount(String id);

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

}
