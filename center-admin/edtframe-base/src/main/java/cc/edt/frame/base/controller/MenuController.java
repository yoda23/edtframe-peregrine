package cc.edt.frame.base.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.base.service.MenuService;
import cc.edt.frame.model.entity.base.Menu;

/**
 * 菜单信息
 *
 * @author 刘钢
 * @date 2018/8/15 9:03
 */
@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Resource
    private ActionResultService actionResultService;
    @Resource
    private MenuService menuService;

    /**
     * 获取所有菜单
     *
     * @author 刘钢
     * @date 2018/8/15 9:04
     */
    @PostMapping("/list")
    public void listMenu() {
        List<Menu> listMenu = menuService.listMenu();
        writerToPageByJsonNoNull(listMenu);
    }
}
