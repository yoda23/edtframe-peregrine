package cc.edt.frame.admin.dao.wechat;

import java.util.List;

import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.wechat.WeChatMenuHistory;

/**
 * 历史微信菜单
 *
 * @author 奚艺轩
 * @date 2017/12/20 9:04
 */
public interface WeChatMenuHistoryDao {
    /**
     * 保存历史微信菜单
     *
     * @param weChatMenuHistory weChatMenuHistory
     * @author 奚艺轩 2017-6-15下午1:08:36
     */
    void saveWeChatMenuHistory(WeChatMenuHistory weChatMenuHistory);

    /**
     * 根据条件查询历史微信菜单
     *
     * @param condition condition
     * @return List<WeChatMenuHistory>
     * @author 奚艺轩 2017-6-15下午1:10:49
     */
    List<WeChatMenuHistory> listWeChatMenuHistoryByCondition(
            FindCondition condition);

    /**
     * 根据备份名称查询历史菜单
     *
     * @param condition condition
     * @return List<WeChatMenuHistory>
     * @author 奚艺轩 2017-6-15下午1:11:15
     */
    List<WeChatMenuHistory> listWeChatMenuHistoryByTitle(
            FindCondition condition);

    /**
     * 根据条件查询一级历史菜单
     *
     * @param condition condition
     * @return List<WeChatMenuHistory>
     * @author 奚艺轩 2017-6-15下午1:12:21
     */
    List<WeChatMenuHistory> listWeChatMenuFirst(FindCondition condition);

    /**
     * 根据条件查询二级历史菜单
     *
     * @param condition condition
     * @return List<WeChatMenuHistory>
     * @author 奚艺轩 2017-6-15下午1:13:03
     */
    List<WeChatMenuHistory> listWeChatMenuByPid(FindCondition condition);

}
