package cc.edt.frame.wechat.controller;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.TableResultService;
import cc.edt.frame.model.condition.WeChatMenuCondition;
import cc.edt.frame.model.condition.params.WeChatMenuConditionParams;
import cc.edt.frame.model.entity.base.User;
import cc.edt.frame.model.entity.wechat.WeChatMenu;
import cc.edt.frame.wechat.service.WeChatMenuService;
import cc.edt.frame.wechat.vo.condition.WeChatMenuConditionVO;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Maps;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 微信菜单
 *
 * @author 刘钢
 * @date 2018/9/17 16:33
 */
@Controller
@CrossOrigin
@RequestMapping("/wechat/menu")
public class WeChatMenuController extends BaseController {
    @Resource
    private WeChatMenuService weChatMenuService;
    @Resource
    private TableResultService tableResultService;

    /**
     * 保存一级菜单
     *
     * @param weChatMenu weChatMenu
     * @author 刘钢
     * @date 2018/9/17 17:12
     */
    @PostMapping("/first/save")
    @ResponseBody
    public void saveWeChatMenuFirst(WeChatMenu weChatMenu) {
        weChatMenu.setId(IdUtil.simpleUUID());
        weChatMenu.setPid("");
        weChatMenu.setRank(1);
        writerToPageByJson(weChatMenuService.saveWeChatMenuFirst(weChatMenu));
    }

    /**
     * 跳转到一级菜单修改界面
     *
     * @param id    id
     * @param model model
     * @return String
     * @author 奚艺轩
     * @date 2017-6-15上午10:58:54
     */
    @GetMapping("/first/id/{id}")
    public String toUpdateWeChatMenuFirst(@PathVariable("id") String id,
                                          Model model) {
        WeChatMenu weChatMenu = weChatMenuService.getWeChatMenuById(id);
        if (weChatMenu != null) {
            model.addAttribute(weChatMenu);
            return "wechatMenu/wechatMenuFirstUpdate";
        }
        return "none";
    }

    /**
     * 修改一级菜单
     *
     * @param weChatMenu weChatMenu
     * @author 奚艺轩
     * @date 2017-6-15上午10:59:50
     */
    @PostMapping("/first/update")
    @ResponseBody
    public void updateWeChatMenuFirst(WeChatMenu weChatMenu) {
        weChatMenu.setPid("");
        ActionResult actionResult = weChatMenuService
                .updateWeChatMenuFirst(weChatMenu);
        writerToPageByJson(actionResult);
    }

    /**
     * 删除菜单
     *
     * @param id id
     * @author 奚艺轩
     * @date 2017-6-15上午10:59:59
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public void deleteWxMenu(@PathVariable("id") String id) {
        ActionResult actionResult = weChatMenuService.deleteWeChatMenu(id);
        writerToPageByJson(actionResult);
    }

    /**
     * 根据条件查询微信菜单
     *
     * @param conditionVO conditionVO
     * @author 奚艺轩
     * @date 2017-6-15上午11:00:47
     */
    @PostMapping("/condition")
    @ResponseBody
    public void getWxMenuByAll(WeChatMenuConditionVO conditionVO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        User user = (User) httpSession.getAttribute("USER");
        WeChatMenuCondition weChatMenuCondition = mapper.map(conditionVO,
                WeChatMenuCondition.class);
        WeChatMenuConditionParams weChatMenuConditionParams = mapper
                .map(conditionVO, WeChatMenuConditionParams.class);
        weChatMenuConditionParams.setUserId(user.getId());
        weChatMenuCondition.setParams(weChatMenuConditionParams);
        List<WeChatMenu> listWeChatMenu = weChatMenuService
                .listWeChatMenuByAll(weChatMenuCondition);
        writerToPageByJson(tableResultService.tableResult(
                Convert.toLong(listWeChatMenu.size()), listWeChatMenu));
    }

    /**
     * 根据ID查找菜单
     *
     * @param id id
     * @author 奚艺轩 2017-6-15上午11:01:07
     */
    @RequestMapping("/id/{id}")
    @ResponseBody
    public void getWxMenuById(@PathVariable("id") String id) {
        WeChatMenu weChatMenu = weChatMenuService.getWeChatMenuById(id);
        writerToPageByJson(weChatMenu);
    }

    /**
     * 保存二级菜单
     *
     * @param weChatMenu weChatMenu
     * @author 奚艺轩
     * @date 2017-6-15上午11:01:48
     */
    @PostMapping("/second/save")
    @ResponseBody
    public void saveWeChatMenuSecond(WeChatMenu weChatMenu) {
        weChatMenu.setId(IdUtil.simpleUUID());
        weChatMenu.setRank(2);
        ActionResult actionResult = weChatMenuService
                .saveWeChatMenuSecond(weChatMenu);
        writerToPageByJson(actionResult);
    }

    /**
     * 跳转到二级菜单修改界面
     *
     * @param id    id
     * @param model model
     * @return String
     * @author 奚艺轩
     * @date 2017-6-15上午11:02:33
     */
    @GetMapping("/second/id/{id}")
    public String toUpdateWxMenuSecond(@PathVariable("id") String id,
                                       Model model) {
        WeChatMenu weChatMenu = weChatMenuService.getWeChatMenuById(id);
        if (weChatMenu != null) {
            // 排序规则
            double order = weChatMenu.getOrder() * 10
                    - (int) weChatMenu.getOrder().doubleValue() * 10;
            weChatMenu.setOrder(order);
            model.addAttribute(weChatMenu);
            return "wechatMenu/wechatMenuSecondUpdate";
        }
        return "none";
    }

    /**
     * 修改二级菜单
     *
     * @param weChatMenu weChatMenu
     * @author 奚艺轩 2017-6-15上午11:02:44
     */
    @PostMapping("/second/update")
    @ResponseBody
    public void updateWxMenu(WeChatMenu weChatMenu) {
        ActionResult actionResult = weChatMenuService
                .updateWeChatMenuSecond(weChatMenu);
        writerToPageByJson(actionResult);
    }

    /**
     * 拉取微信菜单
     *
     * @param weChatMenu weChatMenu
     * @author 奚艺轩
     * @date 2017-6-15上午11:03:57
     */
    @PostMapping("/syncWeChatToLocal")
    @ResponseBody
    public void syncWeChatToLocal(WeChatMenu weChatMenu) {
        ActionResult actionResult = weChatMenuService
                .syncWeChatToLocal(weChatMenu);
        writerToPageByJson(actionResult);
    }

    /**
     * 推送微信菜单
     *
     * @param weChatMenu weChatMenu
     * @author 奚艺轩
     * @date 2017-6-15上午11:04:24
     */
    @PostMapping("/syncLocalToWeChat")
    @ResponseBody
    public void syncLocalToWeChat(WeChatMenu weChatMenu) {
        ActionResult actionResult = weChatMenuService
                .syncLocalToWeChat(weChatMenu);
        writerToPageByJson(actionResult);
    }
}
