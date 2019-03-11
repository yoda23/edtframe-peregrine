package cc.edt.frame.wechat.service;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.condition.WeChatMenuCondition;
import cc.edt.frame.model.entity.wechat.WeChatMenu;

import java.util.List;

/**
 * 微信菜单的接口
 *
 * @author 奚艺轩
 * @date 2017/12/20 9:07
 */
public interface WeChatMenuService {
    /**
     * 保存一级菜单
     *
     * @param weChatMenu weChatMenu
     * @return ActionResult
     * @author 奚艺轩
     * @date 2017-6-15上午10:48:14
     */
    ActionResult saveWeChatMenuFirst(WeChatMenu weChatMenu);

    /**
     * 修改一级菜单
     *
     * @param weChatMenu weChatMenu
     * @return ActionResult
     * @author 奚艺轩
     * @date 2017-6-15上午10:48:14
     */
    ActionResult updateWeChatMenuFirst(WeChatMenu weChatMenu);

    /**
     * 删除菜单
     *
     * @param id id
     * @return ActionResult
     * @author 奚艺轩
     * @date 2017-6-15上午10:48:14
     */
    ActionResult deleteWeChatMenu(String id);

    /**
     * 清空自定义菜单表
     *
     * @param accountId accountId
     * @author 奚艺轩
     * @date 2017-6-15上午10:50:46
     */
    void deleteWeChatMenuByAll(String accountId);

    /**
     * 查询所有一级菜单
     *
     * @param accountId accountId
     * @return List<WeChatMenu>
     * @author 奚艺轩
     * @date 2017-6-15上午10:51:23
     */
    List<WeChatMenu> listWeChatMenuFirst(String accountId);

    /**
     * 保存二级菜单
     *
     * @param weChatMenu WeChatMenu
     * @return ActionResult
     * @author 奚艺轩
     * @date 2017-6-15上午10:51:44
     */
    ActionResult saveWeChatMenuSecond(WeChatMenu weChatMenu);

    /**
     * 修改二级菜单
     *
     * @param weChatMenu weChatMenu
     * @return ActionResult
     * @author 奚艺轩
     * @date 2017-6-15上午10:51:44
     */
    ActionResult updateWeChatMenuSecond(WeChatMenu weChatMenu);

    /**
     * 根据id查询微信菜单
     *
     * @param id id
     * @return WeChatMenu
     * @author 奚艺轩
     * @date 2017-6-15上午10:45:03
     */
    WeChatMenu getWeChatMenuById(String id);

    /**
     * 显示所有自定义菜单数据
     *
     * @param condition condition
     * @return List<WeChatMenu>
     * @author 奚艺轩
     * @date 2017-6-15上午10:55:01
     */
    List<WeChatMenu> listWeChatMenuForView(FindCondition condition);

    /**
     * 根据条件查询微信菜单
     *
     * @param condition condition
     * @return List<WeChatMenu>
     * @author 奚艺轩
     * @date 2017-6-15上午10:46:11
     */
    List<WeChatMenu> listWeChatMenuByAll(FindCondition condition);

    /**
     * 根据pid查询所有二级菜单
     *
     * @param pid pid
     * @return java.util.List<com.edt.entity.WeChatMenu>
     * @author 奚艺轩
     * @date 2017/12/20 10:10
     */
    List<WeChatMenu> listWeChatMenuByPid(String pid);

    /**
     * 从微信同步自定义菜单
     *
     * @param weChatMenu weChatMenu
     * @return ActionResult
     * @author 奚艺轩
     * @date 2017-6-15上午10:53:43
     */
    ActionResult syncWeChatToLocal(WeChatMenu weChatMenu);

    /**
     * 同步菜单到微信
     *
     * @param weChatMenu weChatMenu
     * @return ActionResult
     * @author 奚艺轩
     * @date 2017-6-15上午10:54:03
     */
    ActionResult syncLocalToWeChat(WeChatMenu weChatMenu);

}
