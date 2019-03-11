package cc.edt.frame.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.edt.frame.admin.dao.base.MenuDao;
import cc.edt.frame.base.service.MenuService;
import cc.edt.frame.model.entity.base.Menu;

/**
 * 菜单信息
 *
 * @author 刘钢
 * @date 2018/8/15 9:02
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> listMenu() {
        return menuDao.listMenu();
    }
}
