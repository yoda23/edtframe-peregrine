package cc.edt.frame.admin.dao.wechat;

import java.util.List;

import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.wechat.WeChatMenu;

/**
 * 微信菜单
 *
 * @author 奚艺轩
 * @date 2017/12/20 9:03
 */
public interface WeChatMenuDao {
    /**
     * 保存微信菜单
     *
     * @param weChatMenu weChatMenu
     * @author 奚艺轩 2017-6-15上午10:40:01
     */
    void saveWeChatMenu(WeChatMenu weChatMenu);

    /**
     * 修改微信菜单
     *
     * @param weChatMenu weChatMenu
     * @author 奚艺轩 2017-6-15上午10:40:17
     */
    void updateWeChatMenu(WeChatMenu weChatMenu);

    /**
     * 根据id或者pid删除微信菜单
     *
     * @param id id
     * @author 奚艺轩 2017-6-15上午10:40:32
     */
    void deleteWeChatMenu(String id);

    /**
     * 根据微信帐号id删除微信菜单
     *
     * @param accountId accountId
     * @author 奚艺轩 2017-6-15上午10:40:32
     */
    void deleteWeChatMenuByAll(String accountId);

    /**
     * 根据id查询微信菜单
     *
     * @param id id
     * @return WeChatMenu
     * @author 奚艺轩 2017-6-15上午10:45:03
     */
    WeChatMenu getWeChatMenuById(String id);

    /**
     * 查询所有一级菜单
     *
     * @param accountId accountId
     * @return List<WeChatMenu>
     * @author 奚艺轩 2017-6-15上午10:45:25
     */
    List<WeChatMenu> listWeChatMenuFirst(String accountId);

    /**
     * 根据pid查询所有二级菜单
     *
     * @param pid pid
     * @return List<WeChatMenu>
     * @author 奚艺轩 2017-6-15上午10:45:25
     */
    List<WeChatMenu> listWeChatMenuByPid(String pid);

    /**
     * 根据条件查询微信菜单
     *
     * @param findCondition findCondition
     * @return List<WeChatMenu>
     * @author 奚艺轩 2017-6-15上午10:46:11
     */
    List<WeChatMenu> listWeChatMenuByAll(FindCondition findCondition);
}
